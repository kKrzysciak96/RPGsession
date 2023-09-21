package com.eltescode.rpgsession.features.user.domain.use_case

import com.eltescode.rpgsession.features.user.domain.repository.UserRepository

class UploadUserPhotoUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(byteArray: ByteArray) {
        repository.uploadUserPhoto(byteArray)
    }
}