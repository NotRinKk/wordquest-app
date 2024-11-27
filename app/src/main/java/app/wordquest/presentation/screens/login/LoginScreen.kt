package app.wordquest.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.shared.CustomTextField
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.presentation.screens.welcome.WelcomeScreen
import app.wordquest.presentation.viewmodels.LoginState
import app.wordquest.presentation.viewmodels.LoginViewModel
import app.wordquest.ui.theme.CloudyGrey
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.LightGrey
import app.wordquest.ui.theme.typography

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<LoginViewModel>()

    val loginState by viewModel.loginState.collectAsState()
    
    when (val state = loginState) {
        is LoginState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
        }
        is LoginState.Error -> {
            Text(text = state.message, color = androidx.compose.ui.graphics.Color.Red)
        }
        is LoginState.Success -> {
            navController.navigate(Screen.Home.route)
        }
        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = LightGrey)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 25.dp)
            ) {
                Text(
                    "Авторизация",
                    modifier = Modifier
                        .padding(vertical = 45.dp),
                    style = typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column (
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Введите логин",
                        style = typography.headlineSmall.copy(
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    CustomTextField(
                        value = username,
                        onValueChange = { username = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        text = "Введите пароль",
                        style = typography.headlineSmall.copy(
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    RoundedCornerButton("Авторизация", onClick = { viewModel.login(username, password) },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth())
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Нет аккаунта?",
                        style = typography.bodyMedium.copy(
                            color = CloudyGrey
                        ),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    RoundedCornerButton(
                        "Зарегистрироваться",
                        onClick = { navController.navigate(Screen.Registration.route) },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth())
                }
            }
        }
    }
}
