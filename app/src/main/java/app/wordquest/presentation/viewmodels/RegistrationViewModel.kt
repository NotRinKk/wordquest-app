package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.remote.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> get() = _registrationState

    fun register(username: String, email: String, password: String) {
        _registrationState.value = RegistrationState.Loading

        viewModelScope.launch {
            try {
                val response: HttpResponse = apiService.register(username, email, password)
                if (response.status == HttpStatusCode.Created) {
                    _registrationState.value = RegistrationState.Success
                } else if (response.status == HttpStatusCode.BadRequest) {
                    _registrationState.value = RegistrationState.Error("Пользователь с таким логином уже существует")
                } else {
                    _registrationState.value = RegistrationState.Error("Ошибка на сервере")
                }
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error("Ошибка при регистрации: ${e.message}")
            }
        }
    }
}

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}