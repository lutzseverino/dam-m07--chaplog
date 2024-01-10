package cat.lasalle.chaplog.model

import androidx.lifecycle.ViewModel
import cat.lasalle.chaplog.data.LogUiState
import cat.lasalle.chaplog.data.LogsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.logging.Logger

class LogsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LogsUiState())
    val uiState: StateFlow<LogsUiState> = _uiState

    fun addLog(log: LogUiState) {
        Logger.getAnonymousLogger().info("Adding log $log")
        _uiState.update { it.copy(logs = it.logs + log) }
    }

    fun removeLog(log: LogUiState) {
        Logger.getAnonymousLogger().info("Removing log $log")
        _uiState.update { it.copy(logs = it.logs - log) }
    }

    fun focusLog(log: LogUiState) {
        Logger.getAnonymousLogger().info("Focusing log $log")
        _uiState.update { it.copy(focusedLog = log) }
    }

    fun reset() {
        Logger.getAnonymousLogger().info("Resetting logs")
        _uiState.update { LogsUiState() }
    }
}