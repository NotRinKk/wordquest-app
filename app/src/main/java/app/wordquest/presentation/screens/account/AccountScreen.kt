package app.wordquest.presentation.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.wordquest.presentation.viewmodels.AccountState
import app.wordquest.presentation.viewmodels.AccountViewModel
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography
import app.wordquest.R
import app.wordquest.ui.theme.Lavender

@Composable
fun AccountScreen(navController: NavController) {
    val viewModel = hiltViewModel<AccountViewModel>()
    // Получаем состояние экрана аккаунта из ViewModel
    val accountState by viewModel.accountState.collectAsState()

    // Запросить количество изученных слов
    LaunchedEffect(Unit) {
        viewModel.getStudiedWordsCount()
    }

    when (val state = accountState) {
        is AccountState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
        }

        is AccountState.Error -> {
            Text(text = state.message, color = Color.Red)
        }

        is AccountState.Success -> {
            // Получаем количество изученных слов
            val studiedWordsCount = state.studiedWordsCount

            // Основной контент экрана аккаунта
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkPurple)
            ) {
                // Иконка настроек для перехода на экран настроек
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings),
                        contentDescription = "Settings",
                        tint = White,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_circle),
                    contentDescription = "Account",
                    tint = Lavender,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = White,
                    thickness = 2.dp
                )

                // Количество изученных слов
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Изучено слов: $studiedWordsCount",
                    style = typography.headlineSmall,
                    color = White,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(15.dp)

                )
            }
        }
    }
}