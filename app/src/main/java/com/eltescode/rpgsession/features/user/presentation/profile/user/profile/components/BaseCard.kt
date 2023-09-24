package com.eltescode.rpgsession.features.user.presentation.profile.user.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltescode.rpgsession.core.composable.CustomText

@Composable
fun BaseCard(
    text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 32.sp
) {
    Card(
        modifier = modifier.padding(6.dp),
        elevation = 5.dp,
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = text,
                fontSize = fontSize,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}