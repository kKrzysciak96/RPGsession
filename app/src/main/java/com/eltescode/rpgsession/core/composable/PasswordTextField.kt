package com.eltescode.rpgsession.core.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    password: MutableState<String>,
    isPasswordVisible: MutableState<Boolean>,
    labelText: String,
    isError: Boolean = false,
    modifier: Modifier
) {
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon =
                if (isPasswordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(
                onClick = { isPasswordVisible.value = !isPasswordVisible.value },
                content = { Icon(imageVector = icon, contentDescription = null) }
            )
        },
        label = { Text(text = labelText) },
        isError = isError,
        modifier = modifier
    )
}