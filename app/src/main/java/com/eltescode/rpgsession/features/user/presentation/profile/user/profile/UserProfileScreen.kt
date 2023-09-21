package com.eltescode.rpgsession.features.user.presentation.profile.user.profile

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.eltescode.rpgsession.R
import com.eltescode.rpgsession.core.composable.CustomText
import com.eltescode.rpgsession.core.ui.theme.DarkBrown
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.utils.Screens
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val userData = viewModel.userData.value
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.background_1)))
    val bottomBarBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.background_1)))
    val currentUser = remember { viewModel.getCurrentUser() }
    val userName = remember { mutableStateOf("") }
    val userSurname = remember { mutableStateOf("") }
    val isSettingsDialogVisible = remember { mutableStateOf(false) }
    val photoName = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val takePhotoContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isPhotoTaken ->
            if (isPhotoTaken) {
                val photoFile = photoName.value?.let { File(context.filesDir, it) }
                if (photoFile != null) {
                    val photoByte = photoFile.readBytes()
                    viewModel.onEvent(UserEvent.UpdatePhoto(photoByte))
                    userData?.uid?.let { viewModel.onEvent(UserEvent.GetUserData(it)) }
                }
            }
        }
    )

    LaunchedEffect(key1 = currentUser, block = {
        currentUser?.uid?.let {
            viewModel.onEvent(UserEvent.GetUserData(it))
        }
    })
    LaunchedEffect(key1 = userData, block = {
        userData?.let { user ->
            userName.value = user.name ?: ""
            userSurname.value = user.surname ?: ""
        }
    })
    UserProfileScreen(
        context = context,
        navController = navController,
        onEvent = viewModel::onEvent,
        scope = scope,
        userData = userData,
        imageBrush = imageBrush,
        bottomBarBrush = bottomBarBrush,
        bottomBarContentColor = Color.White,
        isSettingsDialogVisible = isSettingsDialogVisible,
        userName = userName,
        userSurname = userSurname,
        photoName = photoName,
        takePhotoContract = takePhotoContract

    )
}

@Composable
fun UserProfileScreen(
    context: Context,
    navController: NavController,
    onEvent: (UserEvent) -> Unit,
    scope: CoroutineScope,
    userData: CustomUserDisplayable?,
    imageBrush: ShaderBrush,
    bottomBarBrush: ShaderBrush,
    bottomBarContentColor: Color,
    isSettingsDialogVisible: MutableState<Boolean>,
    userName: MutableState<String>,
    userSurname: MutableState<String>,
    photoName: MutableState<String?>,
    takePhotoContract: ManagedActivityResultLauncher<Uri, Boolean>
) {

    Scaffold(

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(imageBrush),
                contentAlignment = Alignment.TopCenter
            ) {

                SignOutIcon(
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    scope.launch {
                        onEvent(UserEvent.SignOut())
                        navController.navigate(Screens.NavigationAuth.route) {
                            popUpTo(Screens.NavigationAuth.route) {
                                inclusive = true
                            }
                        }
                    }
                }
                SettingsIcon(
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    isSettingsDialogVisible.value = !isSettingsDialogVisible.value
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserPicture(
                        userPhoto = userData?.photo ?: "",
                        userName = userData?.name ?: "",
                        userSurname = userData?.surname ?: "",
                        onClick = {
                            photoName.value = "IMG_${System.currentTimeMillis()}.JPG"
                            val photoFile = photoName.value?.let { name ->
                                File(context.filesDir, name)
                            }
                            val photoUri = photoFile?.let { file ->
                                FileProvider
                                    .getUriForFile(context, "custom_file_provider", file)
                            }
                            takePhotoContract.launch(photoUri)
                        }
                    )
                    Text(text = userData.toString())
                }

                if (isSettingsDialogVisible.value) {
                    SettingsDialog(
                        userEmail = userData?.email ?: "",
                        userName = userName,
                        userSurname = userSurname,
                        onUpdate = {
                            val dataToUpdate =
                                mapOf("name" to userName.value, "surname" to userSurname.value)
                            onEvent(UserEvent.UpdateUserData(dataToUpdate))
                            userData?.uid?.let { id -> onEvent(UserEvent.GetUserData(id)) }
                        },
                        onDialogDismiss = {
                            isSettingsDialogVisible.value = !isSettingsDialogVisible.value
                        })
                }


            }
            it
        },
        bottomBar = {
            CustomBottomAppBar(
                modifier = Modifier
                    .height(50.dp)
                    .shadow(5.dp),
                contentColor = bottomBarContentColor,
                backgroundBrush = bottomBarBrush
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}

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

@Composable
fun SettingsIcon(contentDescription: String?, modifier: Modifier, onClickIcon: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Settings,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(16.dp)
            .clickable { onClickIcon() }
    )
}

@Composable
fun UserPicture(userPhoto: String, userName: String, userSurname: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(70.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = userPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClick()
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black), startY = 450F
                        )
                    )
                    .border(BorderStroke(5.dp, color = Color.Black), RoundedCornerShape(70.dp))

            )
            CustomText(
                text = "$userName $userSurname".trim(),
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    backgroundBrush: ShaderBrush,
    contentColor: Color
) {
    Box(
        modifier = modifier.background(DarkBrown),
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
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    tint = contentColor
                )
            }
        }
    }
}

