package app.wordquest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordquest.data.local.repository.WordRepository
import app.wordquest.data.remote.dto.WordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> get() = _homeState

    fun getNewWord(ids: List<Int>) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            try {
                val wordResponse = wordRepository.getNewWord(ids)
                _homeState.value = HomeState.Success(wordResponse!!)
            } catch (e: Exception) {
                _homeState.value = HomeState.Error("Failed to load word: ${e.message}")
            }
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            wordRepository.clearAllData()
        }
    }
}

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val wordResponse: WordResponse) : HomeState()
    data class Error(val message: String) : HomeState()
}