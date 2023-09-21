package com.eltescode.rpgsession.features.user.presentation.utils

import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import kotlinx.coroutines.flow.StateFlow

interface Authenticator<T> {
    suspend fun signIn(credentials: T)
    suspend fun signUp(credentials: T)
    suspend fun signOut()
    fun getCurrentUser(): StateFlow<CustomUserDisplayable?>
}