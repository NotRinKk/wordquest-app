package app.wordquest.presentation.screens.registration

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.screens.login.LoginScreen
import app.wordquest.presentation.viewmodels.RegistrationViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    val viewModel: RegistrationViewModel = viewModel()
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val registrationState = viewModel.registrationState

    // Handle successful registration
    LaunchedEffect(registrationState.success) {
        if (registrationState.success == true) {
            navController.navigate("login_screen") {
                popUpTo("registration_screen") { inclusive = true }
            }
        }
    }

    // UI for registration screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Введите логин", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, Color.Gray),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Введите почту", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, Color.Gray),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Введите пароль", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, Color.Gray),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.register(username.text, email.text, password.text)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Зарегистрироваться")
        }


        Spacer(modifier = Modifier.height(16.dp))
        registrationState.error?.let {
            Text(text = it, color = Color.Red)
        }

        registrationState.message?.let {
            Text(text = it, color = Color.Green)
        }

        if (registrationState.success == null) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    RegistrationScreen(navController = navController)
}
