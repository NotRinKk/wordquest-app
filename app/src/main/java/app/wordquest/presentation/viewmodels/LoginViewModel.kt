package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.remote.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // Состояние для отображения в UI
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading // Начинаем загрузку

        viewModelScope.launch {
            try {
                val response: HttpResponse = apiService.login(username, password)
                if (response.status == HttpStatusCode.OK) {
                    _loginState.value = LoginState.Success // Успешный логин
                } else {
                    _loginState.value = LoginState.Error("Ошибка при авторизации") // Ошибка от сервера
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Ошибка при авторизации: ${e.message}") // Ошибка сети или сервера
            }
        }
    }
}

// Состояние логина
sealed class LoginState {
    object Idle : LoginState() // Начальное состояние
    object Loading : LoginState() // В процессе загрузки
    object Success : LoginState() // Успешная авторизация
    data class Error(val message: String) : LoginState() // Ошибка авторизации
}