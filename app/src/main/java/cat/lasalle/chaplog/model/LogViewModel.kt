package cat.lasalle.chaplog.model

import androidx.lifecycle.ViewModel
import cat.lasalle.chaplog.data.LogUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LogUiState())
    val uiState: StateFlow<LogUiState> = _uiState.asStateFlow()

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateAuthor(author: String) {
        _uiState.update { it.copy(author = author) }
    }

    fun updatePages(pages: Int) {
        _uiState.update { it.copy(pages = pages) }
    }

    fun updateCurrentPage(currentPage: Int) {
        _uiState.update { it.copy(currentPage = currentPage) }
    }

    fun reset() {
        _uiState.update { LogUiState() }
    }
}