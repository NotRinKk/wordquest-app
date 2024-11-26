package app.wordquest.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.wordquest.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.screens.shared.MaxWidthImage
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.ui.theme.*

@Composable
fun WelcomeScreen(navController: NavController) {
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
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        MaxWidthImage(R.drawable.word_quest_logo)

        Spacer(modifier = Modifier.height(120.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Purple),
            modifier = Modifier
                .wrapContentSize()
                .background(Purple, RoundedCornerShape(25.dp))
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                RoundedCornerButton("Зарегистрироваться", onClick = { navController.navigate("registration") })
                RoundedCornerButton("Войти", onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
