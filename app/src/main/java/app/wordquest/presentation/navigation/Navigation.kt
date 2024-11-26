package app.wordquest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.screens.home.HomeScreen
import app.wordquest.presentation.screens.login.LoginScreen
import app.wordquest.presentation.screens.registration.RegistrationScreen
import app.wordquest.presentation.screens.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // NavHost определяет маршруты и экраны
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController) // Приветственный экран
        }
        composable(Screen.Login.route) {
            LoginScreen(navController) // Экран авторизации
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(navController) // Экран регистрации
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)  // Домашний экран
        }
    }
}