package app.wordquest.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography

@Composable
fun GameScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Выберите тип \n викторины",
                textAlign = TextAlign.Center,
                color = White,
                style = typography.headlineLarge,
                modifier = Modifier
                    .padding(vertical = (screenHeight * 0.041).dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height((screenHeight * 0.140).dp))
            RoundedCornerButton(
                "Соотнести ко всем словам \n их переводы",
                onClick = { navController.navigate(Screen.Registration.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            RoundedCornerButton(
                "Сопоставить одно слово с \n переводом", onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}