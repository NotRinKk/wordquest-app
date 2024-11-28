package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.local.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _accountState = MutableStateFlow<AccountState>(AccountState.Loading)
    val accountState: StateFlow<AccountState> get() = _accountState

    // Запрашиваем количество изученных слов
    fun getStudiedWordsCount() {
        viewModelScope.launch {
            _accountState.value = AccountState.Loading
            try {
                // Получаем количество изученных слов
                val studiedWordsCount = wordRepository.getStudiedWordsCount()

                if (studiedWordsCount >= 0) {
                    _accountState.value = AccountState.Success(studiedWordsCount)
                } else {
                    _accountState.value = AccountState.Error("Error retrieving word count")
                }
            } catch (e: Exception) {
                _accountState.value = AccountState.Error("Failed to load studied words: ${e.message}")
            }
        }
    }
}

sealed class AccountState {
    object Loading : AccountState()
    data class Success(val studiedWordsCount: Int) : AccountState()
    data class Error(val message: String) : AccountState()
}