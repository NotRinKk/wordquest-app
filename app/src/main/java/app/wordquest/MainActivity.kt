package app.wordquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import app.wordquest.presentation.navigation.Navigation
import app.wordquest.ui.theme.WordQuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Устанавливаем контент для Activity
        setContent {
            WordQuestTheme {
                // Помещаем компонент навигации в тему
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Ваш компонент навигации, который управляет переходами между экранами
                    Navigation()
                }
            }
        }
    }
}