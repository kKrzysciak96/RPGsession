package com.eltescode.rpgsession.features.user.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.Q)
    override suspend fun doWork(): Result {

        return withContext(Dispatchers.IO) {

            val stringUri = params.inputData.getString(KEY_PHOTO_TO_COMPRESS_URI)

            val compressionThresholdInBytes =
                params.inputData.getLong(KEY_PHOTO_COMPRESSION_THRESHOLD, 0L)

            val uri = Uri.parse(stringUri)

            val oldOrientation = context.contentResolver.openInputStream(uri)?.use {
                ExifInterface(it).getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED
                )
            }


            val bytes = context.contentResolver.openInputStream(uri)?.use {
                it.readBytes()
            } ?: return@withContext Result.failure()

            var bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            bitmap = Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                checkOrientation(oldOrientation),
                true
            )

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

            Result.success(workDataOf(KEY_RESULT_PATH to file.absolutePath))
        }
    }

    companion object {
        const val KEY_PHOTO_TO_COMPRESS_URI = "KEY_PHOTO_TO_COMPRESS_URI"
        const val KEY_PHOTO_COMPRESSION_THRESHOLD = "KEY_PHOTO_COMPRESSION_THRESHOLD"
        const val KEY_RESULT_PATH = "KEY_RESULT_PATH"
    }

}

private fun checkOrientation(orientation: Int?): Matrix {
    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90F)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180F)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270F)
    }
    return matrix
}