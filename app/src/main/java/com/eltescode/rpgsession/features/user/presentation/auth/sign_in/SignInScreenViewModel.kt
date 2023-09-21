package com.eltescode.rpgsession.features.user.presentation.auth.sign_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.user.domain.use_case.UserUseCases
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInSWithEmailViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    ViewModel() {

    private val _user = MutableStateFlow<CustomUserDisplayable?>(null)
    val user = _user.asStateFlow()

    private val _currentUser = mutableStateOf<CustomUserDisplayable?>(null)
    val currentUser: State<CustomUserDisplayable?> = _currentUser

    init {
        getCurrentUser()
    }

    fun onEvent(event: UserEvent) {
        viewModelScope.launch {
            if (event is UserEvent.SignInWithEmail) {
                _currentUser.value =
                    userUseCases.signInUseCase(event.credentials)?.let { CustomUserDisplayable(it) }
            }
        }
    }

    fun getCurrentUser() {
        _currentUser.value = userUseCases.getCurrentUserUseCase()?.let { CustomUserDisplayable(it) }
    }
}