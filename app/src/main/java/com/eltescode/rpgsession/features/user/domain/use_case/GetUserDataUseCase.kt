package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.eltescode.rpgsession.features.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.StateFlow

class GetUserDataUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(id: String): StateFlow<CustomUserDomain?> {
        return repository.getUserData(id)
    }
}