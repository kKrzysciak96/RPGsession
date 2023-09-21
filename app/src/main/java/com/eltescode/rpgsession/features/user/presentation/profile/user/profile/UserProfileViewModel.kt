package com.eltescode.rpgsession.features.user.presentation.profile.user.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.user.domain.use_case.UserUseCases
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    ViewModel() {
    private val _userData = mutableStateOf<CustomUserDisplayable?>(null)
    val userData: State<CustomUserDisplayable?> = _userData
    private var getUserDataJob: Job? = null


    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.GetUserData -> {
                getUserData(event.id)
            }
            is UserEvent.UpdatePhoto -> {
                uploadUserPhoto(event.byteArray)
            }
            is UserEvent.UpdateUserData -> {
                editUserPersonalData(event.dataToUpdate)
            }
            is UserEvent.SignOut -> {
                signOut()
            }
            else -> {}
        }
    }

    private fun editUserPersonalData(map: Map<String, String>) {
        viewModelScope.launch { userUseCases.editUserPersonalDataUseCase(map) }
    }

    private fun getUserData(userId: String) {
        this.getUserDataJob = null
        this.getUserDataJob = viewModelScope.launch {
            userUseCases.getUserDataUseCase(userId).map { user ->
                user?.let { CustomUserDisplayable(it) }
            }.collect {
                _userData.value = it
            }
        }
    }

    private fun uploadUserPhoto(byteArray: ByteArray) {
        viewModelScope.launch { userUseCases.uploadUserPhotoUseCase(byteArray) }
    }

    fun getCurrentUser(): CustomUserDisplayable? {
        return userUseCases.getCurrentUserUseCase()?.let { CustomUserDisplayable(it) }
    }

    private fun signOut() {
        viewModelScope.launch { userUseCases.signOutUseCase() }
    }


}