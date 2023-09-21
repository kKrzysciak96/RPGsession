package com.eltescode.rpgsession.features.career.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eltescode.rpgsession.features.career.presentation.model.CareerDisplayable


@Composable
fun CareerItem(career: CareerDisplayable) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = career.careerName)
    }
}