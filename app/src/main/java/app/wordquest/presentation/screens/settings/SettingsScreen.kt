package app.wordquest.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.wordquest.presentation.screens.shared.SelectionRow
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography


@Composable
fun SettingsScreen(navController: NavController) {
    var selectedLanguage by remember { mutableStateOf("Русский") }
    var selectedLearnLanguage by remember { mutableStateOf("Английский") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple)
    ) {
        Text(
            text = "Настройки",
            style = typography.headlineLarge,
            color = White,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .padding(start = 5.dp)

        )

        HorizontalDivider(thickness = 2.dp, color = White)

        SelectionRow(
            labelText = "Исходный язык:",
            selectedLanguage = selectedLanguage,
            onLanguageSelected = { selectedLanguage = it },
            options = listOf("Русский", "Английский")
        )

        Spacer(modifier = Modifier.height(16.dp))

        SelectionRow(
            labelText = "Целевой язык:",
            selectedLanguage = selectedLearnLanguage,
            onLanguageSelected = { selectedLearnLanguage = it },
            options = listOf("Русский", "Английский")
        )
    }
}