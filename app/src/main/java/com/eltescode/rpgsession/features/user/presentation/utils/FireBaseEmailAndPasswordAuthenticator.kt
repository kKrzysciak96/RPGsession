package com.eltescode.rpgsession.features.user.presentation.utils

import android.util.Log
import com.eltescode.rpgsession.core.extensions.mapToCustomUser
import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FireBaseEmailAndPasswordAuthenticator(
    private val auth: FirebaseAuth,
) :
    Authenticator<EmailCredentials> {

    override suspend fun signIn(credentials: EmailCredentials) {
        auth.signInWithEmailAndPassword(credentials.email, credentials.password)
            .addOnSuccessListener {
                Log.d("AUTHENTICATION", "SUCCESS")
            }
            .addOnFailureListener { Log.d("AUTHENTICATION", "FAILURE") }
    }

    override suspend fun signUp(credentials: EmailCredentials) {
        auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
            .addOnSuccessListener {
                Log.d("AUTHENTICATION", "SUCCESS")
            }
            .addOnFailureListener { Log.d("AUTHENTICATION", "FAILURE") }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun getCurrentUser(): StateFlow<CustomUserDisplayable?> {
        val currentUser = MutableStateFlow<CustomUserDisplayable?>(null)
        currentUser.value = auth.currentUser?.mapToCustomUser()?.let { CustomUserDisplayable(it) }
        return currentUser.asStateFlow()
    }


}




