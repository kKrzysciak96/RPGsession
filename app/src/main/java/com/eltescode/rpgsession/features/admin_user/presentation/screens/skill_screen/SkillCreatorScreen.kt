package com.eltescode.rpgsession.features.admin_user.presentation.screens.skill_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.eltescode.rpgsession.features.admin_user.presentation.utils.AdminUserEvents
import com.eltescode.rpgsession.features.skill.domain.model.Skill

@Composable
fun SkillCreatorScreen(
    viewModel: SkillCreatorViewModel = hiltViewModel()
) {
    val skill = viewModel.skillState.value
    SkillCreatorScreen(
        skill = skill,
        onEvent = viewModel::onEvent,

        )
}

@Composable
fun SkillCreatorScreen(
    skill: Skill,
    onEvent: (AdminUserEvents) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = skill.skillName,
            label = { Text(text = "Enter Talent Name") },
            onValueChange = { onEvent(AdminUserEvents.UpdateSkill(skill.copy(skillName = it))) },
        )
        TextField(
            value = skill.description,
            label = { Text(text = "Enter Talent Description") },
            onValueChange = { onEvent(AdminUserEvents.UpdateSkill(skill.copy(description = it))) })
        Button(onClick = { onEvent(AdminUserEvents.CreateNewSkill()) }) {
        }
    }
}


