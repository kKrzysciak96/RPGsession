package com.eltescode.rpgsession.features.user.presentation.profile.user.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignOutIcon(contentDescription: String?, modifier: Modifier, onClickIcon: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Logout,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(16.dp)
            .clickable { onClickIcon() }
    )
}