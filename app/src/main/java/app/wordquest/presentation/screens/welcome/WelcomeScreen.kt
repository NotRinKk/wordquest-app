package app.wordquest.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.wordquest.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Добро пожаловать!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(R.drawable.word_quest_logo), contentDescription = null)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Войти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("registration") }) {
            Text(text = "Зарегистрироваться")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController = navController)
}
