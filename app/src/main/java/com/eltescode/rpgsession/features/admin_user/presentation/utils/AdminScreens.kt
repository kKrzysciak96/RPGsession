package com.eltescode.rpgsession.features.admin_user.presentation.utils


sealed class AdminScreens(val route: String) {
    object SkillCreatorScreen : AdminScreens("talent_creator_screen")
    object ProfessionCreatorScreen : AdminScreens("profession_creator_screen")

}
