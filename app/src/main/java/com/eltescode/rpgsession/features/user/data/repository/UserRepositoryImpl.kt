package com.eltescode.rpgsession.features.user.data.repository

import android.util.Log
import com.eltescode.rpgsession.core.extensions.mapToCustomUser
import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val cloud: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : UserRepository {

    private var coroutineJob: Job? = null

    override suspend fun getUserData(id: String): StateFlow<CustomUserDomain?> {
        val cloudResult: MutableStateFlow<CustomUserDomain?> = MutableStateFlow(null)

        cloud.collection("users")
            .document(id)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(CustomUserDomain::class.java)
                cloudResult.value = user
                Log.d("DUPA", user.toString())
            }
            .addOnFailureListener { Log.d("DUPA", "FAILURE") }

        return cloudResult.asStateFlow()
    }


    override suspend fun editUserPersonalData(map: Map<String, String>) {
        val userId = auth.currentUser?.uid
        userId?.let {
            cloud.collection("users")
                .document(userId)
                .update(map)
                .addOnSuccessListener {}
                .addOnFailureListener { }
        }
    }

    override suspend fun uploadUserPhoto(bytes: ByteArray): String? {
        val userId = auth.currentUser?.uid
        return try {
            userId?.let {
                storage.getReference("users")
                    .child(it)
                    .putBytes(bytes)
                    .await().storage.downloadUrl.await()
            }.toString().also {
                Log.d("NIEROZUMIEM", "updateUserPhotoInfo_also")
                coroutineJob = CoroutineScope(Job()).launch { updateUserPhotoInfo(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun signIn(credentials: EmailCredentials): CustomUserDomain? {
        return try {
            auth.signInWithEmailAndPassword(credentials.email, credentials.password)
                .await().user?.mapToCustomUser()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun signUp(credentials: EmailCredentials): CustomUserDomain? {
        return try {
            auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
                .await().user?.mapToCustomUser()?.also { createNewUser(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun createNewUser(user: CustomUserDomain) {
        Log.d("REGISTRATION", "${user.uid}")
        cloud.collection("users")
            .document(user.uid)
            .set(user)
    }

    override fun getCurrentUser(): CustomUserDomain? {
        return auth.currentUser?.mapToCustomUser()
    }

    private fun updateUserPhotoInfo(newPhotoUri: String) {
        Log.d("NIEROZUMIEM", "updateUserPhotoInfo")
        val userId = auth.currentUser?.uid
        userId?.let {
            cloud.collection("users")
                .document(userId)
                .update(mapOf("photo" to newPhotoUri))
                .addOnSuccessListener {
                    Log.d("NIEROZUMIEM", "updateUserPhotoInfo_success")
                    coroutineJob = null

                }
                .addOnFailureListener { Log.d("NIEROZUMIEM", "updateUserPhotoInfo_failure") }
        }
    }
}