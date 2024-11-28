package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.wordquest.ui.theme.CarrotOrange
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography

@Composable
fun RoundedCornerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick),
        color = CarrotOrange,
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                color = White,
                style = typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}