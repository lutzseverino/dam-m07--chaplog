package cat.lasalle.chaplog.data

data class LogsUiState(
    val focusedLog : LogUiState? = null,
    val logs : List<LogUiState> = emptyList()
)