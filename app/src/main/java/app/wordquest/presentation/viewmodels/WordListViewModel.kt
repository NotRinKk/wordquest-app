package app.wordquest.presentation.viewmodels

import app.wordquest.data.local.entities.WordTextEntity
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
class WordListViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _audioUrl = MutableStateFlow<String?>(null)
    val audioUrl: StateFlow<String?> = _audioUrl

    private val _selectedWordId = MutableStateFlow<Int?>(null)
    val selectedWordId: StateFlow<Int?> = _selectedWordId

    private val _wordListState = MutableStateFlow<WordListState>(WordListState.Loading)
    val wordListState: StateFlow<WordListState> get() = _wordListState

    private val _selectedWordData = MutableStateFlow<WordData?>(null)
    val selectedWordData: StateFlow<WordData?> get() = _selectedWordData

    // Загружаем все слова из базы данных
    fun getAllWords() {
        viewModelScope.launch {
            _wordListState.value = WordListState.Loading
            try {
                val words = wordRepository.getAllWords()
                if (words.isNotEmpty()) {
                    _wordListState.value = WordListState.Success(words)
                } else {
                    _wordListState.value = WordListState.Error("No words found")
                }
            } catch (e: Exception) {
                _wordListState.value = WordListState.Error("Failed to load words: ${e.message}")
            }
        }
    }

    // Получаем данные для конкретного слова по wordId
    fun getWordDetails(wordId: Int) {
        viewModelScope.launch {
            val wordData = wordRepository.getWordDataById(wordId)
            _selectedWordData.value = wordData
        }
    }

    fun loadAudio(wordId: Int) {
        viewModelScope.launch {
            _audioUrl.value = wordRepository.getAudioByWordId(wordId)?.audioUrl
            _selectedWordId.value = wordId
        }
    }

    // Очистить выбранные данные
    fun clearSelectedWord() {
        _selectedWordData.value = null
        _selectedWordId.value = null
    }
}

// Состояния для списка слов
sealed class WordListState {
    object Loading : WordListState()
    data class Success(val words: List<WordTextEntity>) : WordListState()
    data class Error(val message: String) : WordListState()
}