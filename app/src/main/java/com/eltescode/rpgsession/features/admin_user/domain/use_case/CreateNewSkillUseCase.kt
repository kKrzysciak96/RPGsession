package com.eltescode.rpgsession.features.admin_user.domain.use_case

import com.eltescode.rpgsession.features.admin_user.domain.repository.AdminUserRepository
import com.eltescode.rpgsession.features.skill.domain.model.Skill
import java.util.*

class CreateNewSkillUseCase(private val repository: AdminUserRepository) {

    suspend operator fun invoke(skill: Skill) {

        repository.createNewSkill(
            skill.copy(
                skillName = skill.skillName.lowercase(Locale.ROOT).trim()
            )
        )
    }


}