package com.eltescode.rpgsession

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkManager
import com.eltescode.rpgsession.core.ui.theme.RPGSessionTheme
import com.eltescode.rpgsession.features.admin_user.presentation.screens.profession_screen.ProfessionCreatorScreen
import com.eltescode.rpgsession.features.admin_user.presentation.screens.skill_screen.SkillCreatorScreen
import com.eltescode.rpgsession.features.admin_user.presentation.utils.AdminScreens
import com.eltescode.rpgsession.features.profession.presentation.CareerScreen
import com.eltescode.rpgsession.features.user.presentation.auth.sign_in.SignInScreen
import com.eltescode.rpgsession.features.user.presentation.auth.sign_up.SignUpScreen
import com.eltescode.rpgsession.features.user.presentation.profile.book.BookScreen
import com.eltescode.rpgsession.features.user.presentation.profile.user.profile.UserProfileScreen
import com.eltescode.rpgsession.features.user.presentation.utils.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RPGSessionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.NavigationAuth.route
                    ) {

                        navigation(
                            route = Screens.NavigationAuth.route,
                            startDestination = Screens.SignInScreen.route
                        ) {
                            composable(route = Screens.SignInScreen.route) {
                                SignInScreen(navController = navController)
                            }

                            composable(Screens.SignUpScreen.route) {
                                SignUpScreen(navController = navController)
                            }
                        }

                        navigation(
                            route = Screens.NavigationUser.route,
//                            startDestination = Screens.UserProfileScreen.route
                            startDestination = AdminScreens.ProfessionCreatorScreen.route //admin
                        ) {
                            composable(route = Screens.UserProfileScreen.route) {
                                UserProfileScreen(
                                    workManager = workManager,
                                    navController = navController
                                )
                            }
                            composable(route = Screens.CareerScreen.route) {
                                CareerScreen()
                            }
                            composable(route = Screens.BookScreen.route) {
                                BookScreen()
                            }
                            composable(route = AdminScreens.SkillCreatorScreen.route) {
                                SkillCreatorScreen()
                            }
                            composable(route = AdminScreens.ProfessionCreatorScreen.route) {
                                ProfessionCreatorScreen()
                            }
                        }
                    }
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//            intent?.getParcelableArrayExtra(Intent.EXTRA_STREAM, Uri::class.java)
//        }else {
//            intent.getParcelableArrayExtra(Intent.EXTRA_STREAM)
//        } ?: return
//
//        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
//            .setInputData(
//                workDataOf(
//                    PhotoCompressionWorker.KEY_PHOTO_TO_COMPRESS_URI to uri,
//                    PhotoCompressionWorker.KEY_PHOTO_COMPRESSION_THRESHOLD to 1024 * 20L
//                )
//            )
//            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
//            .build()
//        workManager.enqueue(request)
//    }
}

