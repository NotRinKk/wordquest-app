package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.wordquest.ui.theme.CarrotOrange
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography

@Composable
fun RoundedCornerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Кастомная кнопка с прямоугольной формой и закругленными углами
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
            .height(70.dp)
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
                style = typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    RoundedCornerButton(
        "text",
        onClick = { /* обработка клика */ }
    )
}