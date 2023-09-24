package com.eltescode.rpgsession.features.user.presentation.profile.user.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.eltescode.rpgsession.core.composable.CustomText


@Composable
fun SettingsDialog(
    userEmail: String,
    userName: MutableState<String>,
    userSurname: MutableState<String>,
    onUpdate: () -> Unit,
    onDialogDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDialogDismiss() }) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            ConfirmIcon(modifier = Modifier.align(Alignment.TopEnd), onClick = {
                onDialogDismiss()
                onUpdate()
            })
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(text = userEmail)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Name:",
                        modifier = Modifier.width(100.dp)
                    )
                    TextField(
                        value = userName.value,
                        onValueChange = { userName.value = it },
                        label = { Text(text = "Update Name") }
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Surname:",
                        modifier = Modifier.width(100.dp)
                    )
                    TextField(
                        value = userSurname.value,
                        onValueChange = { userSurname.value = it },
                        label = { Text(text = "Update Name") }
                    )
                }
            }
        }
    }
}

@Composable
fun ConfirmIcon(modifier: Modifier, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Save,
        contentDescription = null,
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() })
}