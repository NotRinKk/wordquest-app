package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    // Inject необходимые зависимости, например, для загрузки данных
) : ViewModel() {

    // Пример состояния для главного экрана
    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState> get() = _homeState

    init {
        // Инициализация данных, например, загрузка списка слов
        loadData()
    }

    private fun loadData() {
        _homeState.value = HomeState.Loading
        viewModelScope.launch {
            try {
                // Замените этот код на вашу логику загрузки данных
                // Например, fetch данные с API
                _homeState.value = HomeState.Success
            } catch (e: Exception) {
                _homeState.value = HomeState.Error("Ошибка при загрузке данных")
            }
        }
    }
}

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    object Success : HomeState()
    data class Error(val message: String) : HomeState()
}