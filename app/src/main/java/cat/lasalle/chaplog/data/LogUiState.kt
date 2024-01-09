package cat.lasalle.chaplog.data

data class LogUiState(
    val title: String = "",
    val author: String = "",
    val pages: Int = 0,
    val currentPage: Int = 0,
)