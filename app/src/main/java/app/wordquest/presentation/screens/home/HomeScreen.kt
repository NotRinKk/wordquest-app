package app.wordquest.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.wordquest.presentation.viewmodels.HomeState
import app.wordquest.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(navController: NavController) {
    // Получаем экземпляр HomeViewModel через Hilt
    val viewModel = hiltViewModel<HomeViewModel>()

    // Получаем состояние из HomeViewModel
    val homeState by viewModel.homeState.collectAsState()

    // Обработка состояния главного экрана
    when (val state = homeState) {
        is HomeState.Loading -> {
            // Показываем индикатор загрузки
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth().aspectRatio(1f))
        }
        is HomeState.Error -> {
            // Показываем сообщение об ошибке
            Text(text = state.message, color = androidx.compose.ui.graphics.Color.Red)
        }
        is HomeState.Success -> {
            // Здесь можно отобразить данные, например, список слов
            Text("Добро пожаловать на главный экран!")
        }
        else -> {}
    }

    // Основное содержимое экрана
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Главная страница")
        Spacer(modifier = Modifier.height(20.dp))

        // Добавьте другие UI элементы, которые вам нужны, например:
        // Button(onClick = { navController.navigate(Screen.WordList.route) }) {
        //     Text("Перейти к списку слов")
        // }
    }
}