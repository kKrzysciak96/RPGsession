package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository

class SignUpUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(credentials: EmailCredentials): CustomUserDomain? {
        return repository.signUp(credentials)
    }
}