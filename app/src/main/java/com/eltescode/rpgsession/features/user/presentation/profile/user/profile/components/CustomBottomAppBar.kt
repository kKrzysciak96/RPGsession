package com.eltescode.rpgsession.features.user.presentation.profile.user.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp
import com.eltescode.rpgsession.core.ui.theme.DarkBrown

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    backgroundBrush: ShaderBrush,
    contentColor: Color
) {

    Box(
        modifier = modifier
            .padding(1.dp)
            .background(DarkBrown),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .background(backgroundBrush)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                ShadingBox()
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = contentColor
                )
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .background(backgroundBrush)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                ShadingBox()
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    tint = contentColor
                )
            }
        }
    }
}

@Composable
private fun ShadingBox(shadeLevel: Float = 0.1F) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = shadeLevel))
    )
}