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
import com.eltescode.rpgsession.core.ui.theme.RPGSessionTheme
import com.eltescode.rpgsession.features.career.presentation.CareerScreen
import com.eltescode.rpgsession.features.user.presentation.auth.sign_in.SignInScreen
import com.eltescode.rpgsession.features.user.presentation.auth.sign_up.SignUpScreen
import com.eltescode.rpgsession.features.user.presentation.profile.book.BookScreen
import com.eltescode.rpgsession.features.user.presentation.profile.user.profile.UserProfileScreen
import com.eltescode.rpgsession.features.user.presentation.utils.Screens
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                            startDestination = Screens.UserProfileScreen.route
                        ) {
                            composable(route = Screens.UserProfileScreen.route) {
                                UserProfileScreen(
                                    navController = navController
                                )
                            }
                            composable(route = Screens.CareerScreen.route) {
                                CareerScreen()
                            }
                            composable(route = Screens.BookScreen.route) {
                                BookScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

