package com.eltescode.rpgsession.features.user.domain.use_case

data class UserUseCases(
    val getUserDataUseCase: GetUserDataUseCase,
    val createNewUserUseCase: CreateNewUserUseCase,
    val signInUseCase: SignInUseCase,
    val signUpUseCase: SignUpUseCase,
    val signOutUseCase: SignOutUseCase,
    val getCurrentUserUseCase: GetCurrentUser,
    val editUserPersonalDataUseCase: EditUserPersonalDataUseCase,
    val uploadUserPhotoUseCase: UploadUserPhotoUseCase
)
