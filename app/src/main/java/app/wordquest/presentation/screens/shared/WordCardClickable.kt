package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.wordquest.data.local.entities.WordTextEntity
import app.wordquest.ui.theme.Lavender
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography

@Composable
fun WordCardClickable(word: WordTextEntity, onClick: (Int) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Lavender),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(word.wordId) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = word.wordText, style = typography.headlineSmall, color = White)
            Text(text = word.translation, style = typography.bodyMedium, color = White)
        }
    }
}