package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository

class CreateNewUserUseCase(private val repository: UserRepository) {

    operator fun invoke(user: CustomUserDomain) {
        repository.createNewUser(user)
    }
}