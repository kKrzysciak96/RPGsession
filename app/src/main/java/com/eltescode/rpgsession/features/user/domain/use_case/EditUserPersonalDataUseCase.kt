package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.repository.UserRepository

class EditUserPersonalDataUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(map: Map<String, String>) {
        repository.editUserPersonalData(map)
    }
}