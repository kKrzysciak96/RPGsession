package com.eltescode.rpgsession.features.profession.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.profession.domain.repository.ProfessionRepository
import com.eltescode.rpgsession.features.profession.presentation.model.CareerDisplayable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(private val repository: ProfessionRepository) :
    ViewModel() {

    private var _careers = MutableStateFlow<List<CareerDisplayable>>(emptyList())
    val careers = _careers.asStateFlow()


    init {
        viewModelScope.launch {
            getCareers()
        }
    }

    private suspend fun getCareers() {
        repository.getCareers().collect() { list ->
            _careers.value = list.map { CareerDisplayable(it) }
        }
    }
}