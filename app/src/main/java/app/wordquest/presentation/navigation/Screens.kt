package app.wordquest.presentation.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Home : Screen("home")
    object Settings : Screen("settings")
    object WordList : Screen("wordlist")
    object Account : Screen("account")
    object NewWord : Screen("newword")
    object Game : Screen("game")

}