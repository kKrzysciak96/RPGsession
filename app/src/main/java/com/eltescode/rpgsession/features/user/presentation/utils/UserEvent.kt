package com.eltescode.rpgsession.features.user.presentation.utils

import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials

sealed class UserEvent() {
    class UpdatePhoto(val byteArray: ByteArray) : UserEvent()
    class UpdateUserData(val dataToUpdate: Map<String, String>) : UserEvent()
    data class GetUserData(val id: String) : UserEvent()
    data class SignInWithEmail(val credentials: EmailCredentials) : UserEvent()
    class SignOut() : UserEvent()
    data class SignUpWithEmail(val credentials: EmailCredentials) : UserEvent()
}
