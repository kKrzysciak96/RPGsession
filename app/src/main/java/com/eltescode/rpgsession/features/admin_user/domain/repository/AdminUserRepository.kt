package com.eltescode.rpgsession.features.admin_user.domain.repository

import com.eltescode.rpgsession.features.profession.domain.model.Profession
import com.eltescode.rpgsession.features.skill.domain.model.Skill

interface AdminUserRepository {

    suspend fun createNewSkill(skill: Skill)
    suspend fun createNewProfession(profession: Profession)
}