package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.repository.UserRepository

class SignOutUseCase(private val repository: UserRepository) {

    suspend operator fun invoke() {
        repository.signOut()
    }
}