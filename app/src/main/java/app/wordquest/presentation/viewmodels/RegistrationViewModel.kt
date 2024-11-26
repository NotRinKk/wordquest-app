package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.remote.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var registrationState: RegistrationState = RegistrationState()

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: HttpResponse = apiService.register(username, password, email)
                if (response.status == HttpStatusCode.Created) {
                    registrationState = RegistrationState(success = true)
                } else if (response.status == HttpStatusCode.BadRequest) {
                    registrationState = RegistrationState(success = false, error = "Пользователь с таким логином уже существует")
                } else {
                    registrationState = RegistrationState(success = false, error = "Ошибка на сервере")
                }
            } catch (e: Exception) {
                registrationState = RegistrationState(error = "Ошибка при регистрации")
            }
        }
    }
}

data class RegistrationState(
    val success: Boolean = false,
    val error: String? = null,
    val message: String? = null
)