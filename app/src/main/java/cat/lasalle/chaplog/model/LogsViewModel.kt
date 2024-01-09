package cat.lasalle.chaplog.model

import androidx.lifecycle.ViewModel
import cat.lasalle.chaplog.data.LogUiState
import cat.lasalle.chaplog.data.LogsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LogsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LogsUiState())
    val uiState: StateFlow<LogsUiState> = _uiState.asStateFlow()

    fun addLog(log: LogUiState) {
        _uiState.update { it.copy(logs = it.logs + log) }
    }

    fun removeLog(log: LogUiState) {
        _uiState.update { it.copy(logs = it.logs - log) }
    }

    fun updateLog(log: LogUiState) {
        _uiState.update { it.copy(logs = it.logs - log + log) }
    }

    fun reset() {
        _uiState.update { LogsUiState() }
    }
}