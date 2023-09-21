package com.eltescode.rpgsession.features.user.presentation.utils

sealed class Screens(val route: String) {
    object SignInScreen : Screens("sign_in_screen")
    object SignUpScreen : Screens("sign_up_screen")
    object UserProfileScreen : Screens("user_profile_screen")
    object CareerScreen : Screens("admin_add_career_screen")
    object BookScreen : Screens("book_screen")
    object NavigationAuth : Screens("nav_auth")
    object NavigationUser : Screens("nav_user")
}
