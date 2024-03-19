package cat.lasalle.chaplog.model

import androidx.lifecycle.ViewModel
import cat.lasalle.chaplog.data.BookLog
import cat.lasalle.chaplog.data.BookLogList
import cat.lasalle.chaplog.database.repository.BookLogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BookLogListViewModel  : ViewModel() {

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

    suspend fun updateBookLog(log: BookLog, bookLogRepository: BookLogRepository) {
        val updatedBookLog = bookLogRepository.updateBookLog(log)
        val updatedLog = BookLog(
            id = updatedBookLog.id,
            title = updatedBookLog.title,
            author = updatedBookLog.author,
            currentPage = updatedBookLog.currentPage,
            pages = updatedBookLog.pages
        )
        _uiState.update { it -> it.copy(logs = it.logs.map { if (it.id == updatedLog.id) updatedLog else it }) }
    }

    suspend fun addBookLog(log: BookLog, bookLogRepository: BookLogRepository) {
        val addedBookLog = bookLogRepository.addBookLog(log)
        val addedLog = BookLog(
            id = addedBookLog.id,
            title = addedBookLog.title,
            author = addedBookLog.author,
            currentPage = addedBookLog.currentPage,
            pages = addedBookLog.pages
        )
        _uiState.update { it.copy(logs = it.logs + addedLog) }
    }
}