package app.wordquest.presentation.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.shared.CustomTextField
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.presentation.viewmodels.RegistrationState
import app.wordquest.presentation.viewmodels.RegistrationViewModel
import app.wordquest.ui.theme.CloudyGrey
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.LightGrey
import app.wordquest.ui.theme.typography

@Composable
fun RegistrationScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<RegistrationViewModel>()

    val registrationState by viewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        if (registrationState is RegistrationState.Success) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Registration.route) { inclusive = true }
            }
        }
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
                    "Регистрация",
                    modifier = Modifier
                        .padding(vertical = 45.dp),
                    style = typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column (
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Придумайте логин",
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
                        text = "Придумайте пароль",
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
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Введите почту",
                        style = typography.headlineSmall.copy(
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(55.dp))
                    RoundedCornerButton("Зарегистрироваться", onClick = { viewModel.register(username, email, password) },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth())
                    Spacer(modifier = Modifier.height(10.dp))
                    if (registrationState is RegistrationState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }

                    if (registrationState is RegistrationState.Error) {
                        Text(
                            text = (registrationState as RegistrationState.Error).message,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}
