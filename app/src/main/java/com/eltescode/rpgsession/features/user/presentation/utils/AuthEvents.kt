package com.eltescode.rpgsession.features.user.presentation.utils

sealed class AuthEvents() {
    object SignInClick : AuthEvents()
    object SignUpButtonClick : AuthEvents()
    object SignOut : AuthEvents()
}
