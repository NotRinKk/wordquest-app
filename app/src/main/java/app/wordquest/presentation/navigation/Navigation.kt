package app.wordquest.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.screens.account.AccountScreen
import app.wordquest.presentation.screens.game.GameScreen
import app.wordquest.presentation.screens.home.HomeScreen
import app.wordquest.presentation.screens.login.LoginScreen
import app.wordquest.presentation.screens.newword.NewWordScreen
import app.wordquest.presentation.screens.registration.RegistrationScreen
import app.wordquest.presentation.screens.settings.SettingsScreen
import app.wordquest.presentation.screens.welcome.WelcomeScreen
import app.wordquest.presentation.screens.wordlist.WordListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            // Показываем BottomBar, если не на экране Welcome, Login или Registration
            if (currentRoute !in listOf(Screen.Welcome.route, Screen.Login.route, Screen.Registration.route)) {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Registration.route) {
                RegistrationScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.NewWord.route) {
                NewWordScreen(navController)
            }
            composable(Screen.WordList.route) {
                WordListScreen(navController)
            }
            composable(Screen.Game.route) {
                GameScreen(navController)
            }
            composable(Screen.Account.route) {
                AccountScreen(navController)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(navController)
            }
        }
    }
}