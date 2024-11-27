package app.wordquest.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.wordquest.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.shared.MaxWidthImage
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.ui.theme.*

@Composable
fun WelcomeScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    var cardHeight by remember { mutableStateOf(0) }
    var cardWidth by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Добро \n пожаловать!",
            textAlign = TextAlign.Center,
            color = White,
            style = typography.headlineLarge.copy(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.25f),
                    offset = androidx.compose.ui.geometry.Offset(-4f, 4f),
                    blurRadius = 4f,
                )
            ),
            modifier = Modifier
                .padding(vertical = (screenHeight * 0.041).dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        MaxWidthImage(R.drawable.word_quest_logo)
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height((screenHeight * 0.140).dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Purple),
            modifier = Modifier
                .wrapContentSize()
                .background(Purple, RoundedCornerShape((screenHeight * 0.03).dp))
                .onSizeChanged { size ->
                    cardWidth = size.width
                    cardHeight = size.height
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.weight(1f))
                RoundedCornerButton("Зарегистрироваться", onClick = { navController.navigate(Screen.Registration.route) },
                    modifier = Modifier
                        .width((cardWidth * 0.3).dp)
                        .height((cardHeight * 0.15).dp))
                RoundedCornerButton("Войти", onClick = { navController.navigate(Screen.Login.route) },
                    modifier = Modifier
                        .width((cardWidth * 0.3).dp)
                        .height((cardHeight * 0.15).dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCustomButton() {
//    val mockNavController = rememberNavController() // Используем простую реализацию навигации
//
//    // Вставляем компонент WelcomeScreen в превью
//    WelcomeScreen(navController = mockNavController)
//}
