package app.wordquest.presentation.screens.newword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.wordquest.presentation.screens.shared.OvalTextCard
import app.wordquest.presentation.viewmodels.NewWordState
import app.wordquest.presentation.viewmodels.NewWordViewModel
import app.wordquest.presentation.screens.shared.WordCard
import app.wordquest.ui.theme.*

@Composable
fun NewWordScreen(navController: NavController) {

    val viewModel = hiltViewModel<NewWordViewModel>()

    // Получаем состояние с помощью collectAsState()
    val newWordState by viewModel.newWordState.collectAsState()

    // Запрашиваем последнее добавленное слово при старте экрана
    LaunchedEffect(Unit) {
        viewModel.getLastWord()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        when (newWordState) {
            is NewWordState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
            }
            is NewWordState.Error -> {
                // Отображаем сообщение об ошибке
                Text(
                    text = (newWordState as NewWordState.Error).message,
                    color = Color.Red
                )
            }
            is NewWordState.Success -> {
                // Получаем данные о последнем слове
                val wordData = (newWordState as NewWordState.Success).wordData

                // Отображаем слово
                Text(
                    text = "Новое слово",
                    style = typography.headlineLarge,
                    color = White,
                    modifier = Modifier
                        .padding(top = 90.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 15.dp, vertical = 3.dp)
                )
                OvalTextCard(wordData.word)

                // Отображаем перевод
                Text(
                    text = wordData.translation,
                    style = typography.headlineLarge,
                    color = White,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 15.dp, vertical = 3.dp)
                )

                // Выводим другие данные
                WordCard(wordData)
            }
        }

        Spacer(modifier = Modifier.weight(2f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewWord() {
    val mockNavController = rememberNavController()
    NewWordScreen(navController = mockNavController)
}