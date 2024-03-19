package cat.lasalle.chaplog.model

import androidx.lifecycle.ViewModel
import cat.lasalle.chaplog.data.BookLog
import cat.lasalle.chaplog.data.BookLogList
import cat.lasalle.chaplog.database.repository.BookLogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BookLogListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BookLogList())
    val uiState: StateFlow<BookLogList> = _uiState

    fun add(log: BookLog) {
        _uiState.update { it.copy(logs = it.logs + log) }
    }

    fun remove(log: BookLog) {
        _uiState.update { it.copy(logs = it.logs - log) }
    }

    fun focus(log: BookLog) {
        _uiState.update { it.copy(focusedLog = log) }
    }

    fun reset() {
        _uiState.update { BookLogList() }
    }

    suspend fun updateData(bookLogRepository: BookLogRepository) {
        val bookLogs = bookLogRepository.getBookLogs().map { entity ->
            BookLog(
                id = entity.id,
                title = entity.title,
                author = entity.author,
                currentPage = entity.currentPage,
                pages = entity.pages
            )
        }
        _uiState.update { it.copy(logs = bookLogs) }
    }
}