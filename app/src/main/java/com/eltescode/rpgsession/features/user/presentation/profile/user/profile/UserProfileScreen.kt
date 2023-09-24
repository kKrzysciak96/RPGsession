package com.eltescode.rpgsession.features.user.presentation.profile.user.profile

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.work.*
import com.eltescode.rpgsession.R
import com.eltescode.rpgsession.features.user.presentation.model.CustomUserDisplayable
import com.eltescode.rpgsession.features.user.presentation.profile.user.profile.components.*
import com.eltescode.rpgsession.features.user.presentation.utils.PhotoCompressionWorker
import com.eltescode.rpgsession.features.user.presentation.utils.Screens
import com.eltescode.rpgsession.features.user.presentation.utils.UserEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun UserProfileScreen(
    workManager: WorkManager,
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val userData = viewModel.userData.value
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.background_1)))
    val bottomBarBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.background_1)))
    val userName = remember { mutableStateOf("") }
    val userSurname = remember { mutableStateOf("") }
    val isSettingsDialogVisible = remember { mutableStateOf(false) }
    val photoName = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val workResult = viewModel.workId?.let { id ->
        workManager.getWorkInfoByIdLiveData(id).observeAsState().value
    }

    val takePhotoContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isPhotoTaken ->
            if (isPhotoTaken) {
                val photoFile = photoName.value?.let { File(context.filesDir, it) }
                if (photoFile != null) {
                    val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
                        .setInputData(
                            workDataOf(
                                PhotoCompressionWorker.KEY_PHOTO_TO_COMPRESS_URI to photoFile.toUri()
                                    .toString(),
                                PhotoCompressionWorker.KEY_PHOTO_COMPRESSION_THRESHOLD to 1024 * 200L
                            )
                        )
                        .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
                        .build()
                    viewModel.updateWorkId(request.id)
                    workManager.enqueue(request)
                }
            }
        }
    )
    LaunchedEffect(key1 = workResult?.outputData, block = {
        if (workResult?.outputData != null) {
            val filePAth = workResult.outputData.getString(PhotoCompressionWorker.KEY_RESULT_PATH)
            filePAth?.let {
                val bytes = File(filePAth).readBytes()
                viewModel.onEvent(UserEvent.UpdatePhoto(bytes))
                viewModel.updateWorkId(null)
            }
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
    val list = remember {
        listOf(
            "Your Sheets",
            "Favourite Careers",
            "Favourite Magic",
            "Notes",
            "Career Creator",
            "Cosik"
        )
    }
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
                        .padding(top = 32.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, bottom = 35.dp, start = 8.dp, end = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        items(list) { title ->
                            BaseCard(
                                text = title,
                                modifier = Modifier
                                    .size(175.dp)
                                    .padding(bottom = 12.dp),
                                fontSize = 18.sp
                            )
                        }
                    }
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





