package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.local.repository.WordRepository
import app.wordquest.domain.model.WordData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWordViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _newWordState = MutableStateFlow<NewWordState>(NewWordState.Loading)
    val newWordState: StateFlow<NewWordState> get() = _newWordState

    // Функция для получения последнего слова
    fun getLastWord() {
        viewModelScope.launch {
            _newWordState.value = NewWordState.Loading
            try {
                // Получаем последний wordId из таблицы word_ids
                val lastWordId = wordRepository.getLastWordId()

                if (lastWordId != null) {
                    // Получаем все данные для этого wordId
                    val wordData = wordRepository.getWordDataById(lastWordId)

                    if (wordData != null) {
                        _newWordState.value = NewWordState.Success(wordData)
                    } else {
                        _newWordState.value = NewWordState.Error("No data found for the last word")
                    }
                } else {
                    _newWordState.value = NewWordState.Error("No wordId found")
                }
            } catch (e: Exception) {
                _newWordState.value = NewWordState.Error("Failed to load word: ${e.message}")
            }
        }
    }
}

sealed class NewWordState {
    object Loading : NewWordState() // Состояние загрузки
    data class Success(val wordData: WordData) : NewWordState() // Успешная загрузка
    data class Error(val message: String) : NewWordState() // Ошибка
}
