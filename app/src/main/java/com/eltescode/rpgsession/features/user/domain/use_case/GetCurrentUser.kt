package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.StateFlow

class GetCurrentUser(private val repository: UserRepository) {

    operator fun invoke(): CustomUserDomain? {
        return repository.getCurrentUser()
    }
}