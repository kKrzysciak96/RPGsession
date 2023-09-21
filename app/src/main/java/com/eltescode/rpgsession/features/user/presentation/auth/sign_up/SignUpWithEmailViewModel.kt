package com.eltescode.rpgsession.features.user.presentation.auth.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.user.domain.use_case.UserUseCases
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpWithEmailViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _currentUser = mutableStateOf<CustomUserDisplayable?>(null)
    val currentUser: State<CustomUserDisplayable?> = _currentUser

    fun onEvent(event: UserEvent) {
        viewModelScope.launch {
            if (event is UserEvent.SignUpWithEmail) {
                _currentUser.value =
                    userUseCases.signUpUseCase(event.credentials)?.let { CustomUserDisplayable(it) }
            }
        }
    }


}