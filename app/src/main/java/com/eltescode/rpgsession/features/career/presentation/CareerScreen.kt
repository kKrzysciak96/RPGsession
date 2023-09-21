package com.eltescode.rpgsession.features.career.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.eltescode.rpgsession.features.career.presentation.model.CareerDisplayable

@Composable
fun CareerScreen(
    viewModel: CareerViewModel = hiltViewModel()
) {
    val careers = viewModel.careers.collectAsState()

    CareerScreen(careers = careers.value)
}

@Composable
fun CareerScreen(
    careers: List<CareerDisplayable>
) {
    LazyColumn(content = {
        items(careers) {
            CareerItem(career = it)
        }
    })
}