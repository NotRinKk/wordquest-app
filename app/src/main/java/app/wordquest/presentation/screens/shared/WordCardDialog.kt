package app.wordquest.presentation.screens.shared

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.wordquest.domain.model.WordData
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.YellowOrange
import app.wordquest.ui.theme.typography

@Composable
fun WordCardDialog(wordData: WordData, audioUrl: String?, onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    var isPlaying by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer() }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            colors = CardDefaults.cardColors(containerColor = YellowOrange),
            modifier = Modifier
                .height(380.dp)
                .width(380.dp)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                // Слово и перевод
                Text(text = "Слово: ${wordData.word}", style = typography.headlineSmall, color = White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Перевод: ${wordData.translation}", style = typography.bodyMedium, color = White)
                Spacer(modifier = Modifier.height(16.dp))

                // Определения и их переводы
                if (wordData.definitions.isNotEmpty()) {
                    Text(text = "Определение:", style = typography.bodyMedium, color = White)
                    wordData.definitions.forEachIndexed { index, definition ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "${index + 1}. $definition", style = typography.bodyMedium, color = White)
                        if (wordData.definitionTranslations.size > index) {
                            Text(
                                text = "Перевод определения: ${wordData.definitionTranslations[index]}",
                                style = typography.bodyMedium,
                                color = White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Предложения и их переводы
                if (wordData.sentences.isNotEmpty()) {
                    Text(text = "Примеры:", style = typography.bodyMedium, color = White)
                    wordData.sentences.forEachIndexed { index, sentence ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "${index + 1}. $sentence", style = typography.bodyMedium, color = White)
                        if (wordData.sentenceTranslations.size > index) {
                            Text(
                                text = "Перевод: ${wordData.sentenceTranslations[index]}",
                                style = typography.bodyMedium,
                                color = White
                            )
                        }
                    }
                }

                // Кнопка для воспроизведения аудио, если URL доступен
                if (audioUrl != null && audioUrl != "") {
                    Spacer(modifier = Modifier.height(16.dp))
                    RoundedCornerButton(
                        onClick = {
                        if (isPlaying) {
                            mediaPlayer.stop()
                        } else {
                            mediaPlayer.reset()
                            mediaPlayer.setDataSource(audioUrl)
                            mediaPlayer.prepareAsync()
                            mediaPlayer.setOnPreparedListener { mp -> mp.start() } // Запуск аудио
                        }
                        isPlaying = !isPlaying // Переключение состояния воспроизведения
                    },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                            .height(100.dp)
                            .fillMaxWidth(),
                        text = if (isPlaying) "Остановить" else "Воспроизвести")
                }
            }
        }
    }
}