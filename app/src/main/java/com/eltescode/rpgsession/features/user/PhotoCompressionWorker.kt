package com.eltescode.rpgsession.features.user

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.location.LocationRequestCompat.Quality
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

class PhotoCompressionWorker(private val context: Context, private val params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val stringUri = params.inputData.getString(KEY_PHOTO_TO_COMPRESS_URI)
            val compressionThresholdInBytes =
                params.inputData.getLong(KEY_PHOTO_COMPRESSION_THRESHOLD, 0L)
            val uri = Uri.parse(stringUri)
            val bytes = context.contentResolver.openInputStream(uri)?.use {
                it.readBytes()
            } ?: return@withContext Result.failure()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            var outputBytes: ByteArray
            var quality = 100
            do {
                val outputStream = ByteArrayOutputStream()
                outputStream.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                    outputBytes = outputStream.toByteArray()
                    quality -= (quality * 0.1).roundToInt()
                }
            } while (outputBytes.size > compressionThresholdInBytes && quality > 5)
            val file = File(context.cacheDir, "${params.id}.jpg")
            file.writeBytes(outputBytes)
            stringUri?.let { File(it).delete() }
            Result.success(
                workDataOf(KEY_RESULT_PATH to file.absolutePath)
            )
        }
    }

    companion object {
        const val KEY_PHOTO_TO_COMPRESS_URI = "KEY_PHOTO_TO_COMPRESS_URI"
        const val KEY_PHOTO_COMPRESSION_THRESHOLD = "KEY_PHOTO_COMPRESSION_THRESHOLD"
        const val KEY_RESULT_PATH = "KEY_RESULT_PATH"
    }


}