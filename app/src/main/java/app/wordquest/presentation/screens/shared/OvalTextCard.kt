package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography

@Composable
fun OvalTextCard(text: String) {
        Box(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .height(200.dp)

        ) {
            Image(
                painter = painterResource(id = app.wordquest.R.drawable.ovalcard),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = text,
                style = typography.bodyMedium,
                color = White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
}

@Preview()
@Composable
fun OvalPreview() {

    OvalTextCard("Text")
}