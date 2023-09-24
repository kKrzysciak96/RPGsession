package com.eltescode.rpgsession.features.admin_user.presentation.screens.skill_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.admin_user.data.repository.textPo
import com.eltescode.rpgsession.features.admin_user.domain.use_case.AdminUseCases
import com.eltescode.rpgsession.features.admin_user.presentation.utils.AdminUserEvents
import com.eltescode.rpgsession.features.skill.domain.model.Skill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillCreatorViewModel @Inject constructor(private val adminUseCases: AdminUseCases) :
    ViewModel() {

    init {
//        viewModelScope.launch {
//            textPo.forEach {
//                adminUseCases.createNewSkillUseCase(Skill(skillName = it, "", "", ""))
//            }
//        }
    }

    private val _skillState =
        mutableStateOf(Skill(skillName = "", skillType = "", characteristic = "", description = ""))
    val skillState: State<Skill> = _skillState

    fun onEvent(event: AdminUserEvents) {
        if (event is AdminUserEvents.CreateNewSkill) {
            createNewSkill()
        }
        if (event is AdminUserEvents.UpdateSkill) {
            updateSkill(event.skill)
        }
    }

    private fun createNewSkill() {
        viewModelScope.launch { adminUseCases.createNewSkillUseCase(skillState.value) }
    }

    private fun updateSkill(skill: Skill) {
        _skillState.value = skill
    }

}