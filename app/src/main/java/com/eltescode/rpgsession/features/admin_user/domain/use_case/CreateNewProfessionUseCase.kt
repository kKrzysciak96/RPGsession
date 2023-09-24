package com.eltescode.rpgsession.features.admin_user.domain.use_case

import com.eltescode.rpgsession.features.admin_user.domain.repository.AdminUserRepository
import com.eltescode.rpgsession.features.profession.domain.model.Profession

class CreateNewProfessionUseCase(private val repository: AdminUserRepository) {

    suspend operator fun invoke(profession: Profession) {
        repository.createNewProfession(profession)
    }
}