package com.eltescode.rpgsession.features.user.presentation.auth.sign_in

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(

    navController: NavController,
    viewModel: SignInSWithEmailViewModel = hiltViewModel(),
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currentUser = viewModel.currentUser.value
    val context = LocalContext.current

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
    SignInScreen(
        navController = navController,
        onEvent = viewModel::onEvent,
        email = email,
        password = password,
        isPasswordVisible = isPasswordVisible,
        imageBrush = imageBrush
    )
}

@Composable
fun SignInScreen(

    navController: NavController,
    onEvent: (UserEvent) -> Unit,
    email: MutableState<String>,
    password: MutableState<String>,
    isPasswordVisible: MutableState<Boolean>,
    imageBrush: ShaderBrush
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(imageBrush),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onEvent(UserEvent.SignInWithEmail(EmailCredentials("admin@adres.pl", "android123")))
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text(text = "Admin login")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
                        text = "Login",
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
                    OutlinedButton(
                        onClick = {
                            onEvent(
                                UserEvent.SignInWithEmail(
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
                            disabledBackgroundColor = Color.LightGray
                        ),
                        border = BorderStroke(2.dp, Color.Black),
                        modifier = Modifier.width(150.dp),
                        shape = CircleShape
                    ) {
                        Text(text = "Sign In", fontFamily = fontFamily_croissant, fontSize = 20.sp)
                    }

                }
            }
            Row(
                modifier =
                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    Modifier.fillMaxWidth()
                else
                    Modifier
                        .fillMaxWidth()
                        .padding(48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowRight,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 16.dp, end = 16.dp)
                )
                OutlinedButton(
                    onClick = {
                        navController.navigate(Screens.SignUpScreen.route)
                    }, colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.LightGray
                    ),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = CircleShape,
                    modifier = Modifier
                        .width(100.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        fontFamily = fontFamily_croissant,
                        fontSize = 12.sp,
                        modifier = Modifier
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 16.dp, end = 16.dp)
                )
            }
        }

    }
}



