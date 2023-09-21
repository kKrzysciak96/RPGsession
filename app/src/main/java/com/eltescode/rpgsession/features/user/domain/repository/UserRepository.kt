package com.eltescode.rpgsession.features.user.domain.repository

import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun getUserData(id: String): StateFlow<CustomUserDomain?>
    fun createNewUser(user: CustomUserDomain)
    suspend fun signIn(credentials: EmailCredentials): CustomUserDomain?
    suspend fun signUp(credentials: EmailCredentials): CustomUserDomain?
    suspend fun signOut()
    fun getCurrentUser(): CustomUserDomain?
    suspend fun editUserPersonalData(map: Map<String, String>)
    suspend fun uploadUserPhoto(bytes: ByteArray): String?
}