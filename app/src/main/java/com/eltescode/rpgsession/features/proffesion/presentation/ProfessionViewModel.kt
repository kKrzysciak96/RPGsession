package com.eltescode.rpgsession.features.proffesion.presentation

import androidx.lifecycle.ViewModel
import com.eltescode.rpgsession.features.proffesion.domain.ProfessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfessionViewModel @Inject constructor(private val repository: ProfessionRepository) :
    ViewModel() {

    suspend fun get(): String {
        return repository.getProfessions()
    }
}