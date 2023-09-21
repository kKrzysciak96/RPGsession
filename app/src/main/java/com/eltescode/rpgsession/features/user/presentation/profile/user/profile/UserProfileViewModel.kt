package com.eltescode.rpgsession.features.user.presentation.profile.user.profile

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.user.domain.use_case.UserUseCases
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    ViewModel() {
    private val _userData = mutableStateOf<CustomUserDisplayable?>(null)
    val userData: State<CustomUserDisplayable?> = _userData
    private var getUserDataJob: Job? = null

    var uncompressedUri: Uri? by mutableStateOf(null)
        private set
    var compressedBitmap: Bitmap? by mutableStateOf(null)
        private set
    var workId: UUID? by mutableStateOf(null)
        private set

    fun updateUncompressedUri(uri: Uri?) {
        uncompressedUri = uri
    }

    fun updateCompressedBitmap(bitmap: Bitmap?) {
        compressedBitmap = bitmap
    }

    fun updateWorkId(uuid: UUID?) {
        workId = uuid
    }

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

    fun getCurrentUser(): CustomUserDisplayable? {
        return userUseCases.getCurrentUserUseCase()?.let { CustomUserDisplayable(it) }
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
        viewModelScope.launch {
            userUseCases.uploadUserPhotoUseCase(byteArray)?.also { updateUserDataState(it) }
        }
    }


    private fun signOut() {
        viewModelScope.launch { userUseCases.signOutUseCase() }
    }

    private fun updateUserDataState(newPhoto: String) {
        _userData.value = userData.value?.copy(photo = newPhoto)
    }


}