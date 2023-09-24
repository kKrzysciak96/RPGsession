package com.eltescode.rpgsession.features.admin_user.presentation.utils

import com.eltescode.rpgsession.features.skill.domain.model.Skill

sealed class AdminUserEvents() {
    class CreateNewSkill() : AdminUserEvents()
    class UpdateSkill(val skill: Skill) : AdminUserEvents()
}
