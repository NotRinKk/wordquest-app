package app.wordquest.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.welcome.WelcomeScreen
import app.wordquest.presentation.viewmodels.LoginState
import app.wordquest.presentation.viewmodels.LoginViewModel
//import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<LoginViewModel>()

    // Получаем текущее состояние из ViewModel
    val loginState by viewModel.loginState.collectAsState()

    // Обработка состояния авторизации
    when (val state = loginState) {
        is LoginState.Loading -> {
            // Показываем индикатор загрузки
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
        }
        is LoginState.Error -> {
            // Показываем сообщение об ошибке
            Text(text = state.message, color = androidx.compose.ui.graphics.Color.Red)
        }
        is LoginState.Success -> {
            // Переход на главный экран, если логин успешен
            navController.navigate(Screen.Home.route)
        }
        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login")
        Spacer(modifier = Modifier.height(20.dp))

        // Поле для ввода имени пользователя
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Поле для ввода пароля
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Кнопка авторизации
        Button(onClick = {
            viewModel.login(username, password) // Вызов метода для логина
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Кнопка для перехода на экран регистрации
        Button(onClick = { navController.navigate(Screen.Registration.route) }) {
            Text("Go to Register")
        }
    }
}
