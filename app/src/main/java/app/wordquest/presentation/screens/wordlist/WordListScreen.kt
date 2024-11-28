package app.wordquest.presentation.screens.wordlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.wordquest.presentation.screens.shared.WordCardClickable
import app.wordquest.presentation.screens.shared.WordCardDialog
import app.wordquest.presentation.viewmodels.WordListState
import app.wordquest.presentation.viewmodels.WordListViewModel
import app.wordquest.ui.theme.*

@Composable
fun WordListScreen(navController: NavController) {

    val viewModel = hiltViewModel<WordListViewModel>()

    // Получаем состояние списка слов и данных для выбранного слова
    val wordListState by viewModel.wordListState.collectAsState()
    val selectedWordData by viewModel.selectedWordData.collectAsState()
    val selectedWordAudioUrl by viewModel.audioUrl.collectAsState()

    // Загружаем список слов при старте экрана
    LaunchedEffect(Unit) {
        viewModel.getAllWords()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(DarkPurple)
    ) {
        when (wordListState) {
            is WordListState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
            }
            is WordListState.Error -> {
                Text(
                    text = (wordListState as WordListState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is WordListState.Success -> {
                val words = (wordListState as WordListState.Success).words

                // LazyColumn для списка слов
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(words) { word ->
                        WordCardClickable(
                            word = word,
                            onClick = {
                                // Получаем подробную информацию о слове
                                viewModel.getWordDetails(word.wordId)
                                viewModel.loadAudio(word.wordId)
                            }
                        )
                    }
                }
            }
        }

        Text(
            text = "Для того чтобы посмотреть перевод слова, достаточно нажать на нужное слово",
            style = typography.bodyMedium,
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))

        // Показываем карточку с подробной информацией и аудио, если данные выбраны
        selectedWordData?.let { wordData ->
            WordCardDialog(
                wordData = wordData,
                audioUrl = selectedWordAudioUrl,
                onDismiss = { viewModel.clearSelectedWord() }
            )
        }
    }
}