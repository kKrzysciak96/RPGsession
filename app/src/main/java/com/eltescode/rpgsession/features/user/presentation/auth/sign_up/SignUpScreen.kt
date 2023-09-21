package com.eltescode.rpgsession.features.user.presentation.auth.sign_up

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eltescode.rpgsession.R
import com.eltescode.rpgsession.core.composable.PasswordTextField
import com.eltescode.rpgsession.core.ui.theme.fontFamily_croissant
import com.eltescode.rpgsession.features.user.data.utils.EmailCredentials
import com.eltescode.rpgsession.features.user.presentation.utils.Screens
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import kotlinx.coroutines.CoroutineScope

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpWithEmailViewModel = hiltViewModel()
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatedPassword = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currentUser = viewModel.currentUser.value

    LaunchedEffect(key1 = currentUser, block = {
        if (currentUser != null) {
            navController.navigate(Screens.NavigationUser.route) {
                popUpTo(Screens.NavigationAuth.route) {
                    inclusive = true
                }
            }
        }
    })
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.background_1)))
    SignUpScreen(
        navController = navController,
        onEvent = viewModel::onEvent,
        email = email,
        password = password,
        repeatedPassword = repeatedPassword,
        isPasswordVisible = isPasswordVisible,
        scope = scope,
        imageBrush = imageBrush
    )
}

@Composable
fun SignUpScreen(
    navController: NavController,
    onEvent: (UserEvent) -> Unit,
    email: MutableState<String>,
    password: MutableState<String>,
    repeatedPassword: MutableState<String>,
    isPasswordVisible: MutableState<Boolean>,
    scope: CoroutineScope,
    imageBrush: ShaderBrush
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(imageBrush),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(2.dp), elevation = 5.dp,
            backgroundColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Register",
                    fontFamily = fontFamily_croissant,
                    fontSize = 32.sp,
                    modifier = Modifier
                )
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    modifier = Modifier.padding(bottom = 12.dp),
                    label = { Text(text = "Enter email") })
                PasswordTextField(
                    password = password,
                    isPasswordVisible = isPasswordVisible,
                    labelText = "Enter Password",
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                PasswordTextField(
                    password = repeatedPassword,
                    isPasswordVisible = isPasswordVisible,
                    labelText = "Repeat password",
                    isError = password.value != repeatedPassword.value,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                OutlinedButton(
                    onClick = {
                        onEvent(
                            UserEvent.SignUpWithEmail(
                                EmailCredentials(
                                    email.value,
                                    password.value
                                )
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent
                    ),
                    border = BorderStroke(2.dp, Color.Black),
                    modifier = Modifier.width(150.dp),
                    shape = CircleShape
                ) {
                    Text(text = "Sign Up", fontFamily = fontFamily_croissant, fontSize = 20.sp)
                }
            }
        }
    }
}

