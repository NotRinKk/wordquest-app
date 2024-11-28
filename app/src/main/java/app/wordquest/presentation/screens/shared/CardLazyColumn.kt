package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.wordquest.domain.model.WordData
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.YellowOrange
import app.wordquest.ui.theme.typography

@Composable
fun WordCard(wordData: WordData) {
    val scrollState = rememberScrollState()
    Card(
        colors = CardDefaults.cardColors(containerColor = YellowOrange),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Слово и перевод
            Text(text = "Слово: ${wordData.word}", style = typography.headlineSmall,
                color = White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Перевод: ${wordData.translation}", style = typography.bodyMedium,
                color = White)
            Spacer(modifier = Modifier.height(16.dp))

            // Определения и их переводы
            if (wordData.definitions.isNotEmpty()) {
                Text(text = "Определение:", style = typography.bodyMedium,
                    color = White)
                wordData.definitions.forEachIndexed { index, definition ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${index + 1}. $definition", style = typography.bodyMedium,
                        color = White)
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
                Text(text = "Примеры:", style = typography.bodyMedium,
                    color = White)
                wordData.sentences.forEachIndexed { index, sentence ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${index + 1}. $sentence", style = typography.bodyMedium,
                        color = White)
                    if (wordData.sentenceTranslations.size > index) {
                        Text(
                            text = "Перевод: ${wordData.sentenceTranslations[index]}",
                            style = typography.bodyMedium,
                            color = White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWordCard() {
    val wordData = WordData(
        word = "Example",
        translation = "Пример",
        definitions = listOf("A thing characteristic of its kind.", "A model or pattern."),
        definitionTranslations = listOf("Пример", "Модель"),
        sentences = listOf("This is an example.", "For example, let me show."),
        sentenceTranslations = listOf("Это пример.", "Например, давайте я покажу.")
    )

    WordCard(wordData = wordData)
}