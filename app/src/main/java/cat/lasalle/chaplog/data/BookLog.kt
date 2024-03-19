package cat.lasalle.chaplog.data

data class BookLog(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val currentPage: Int = 0,
    val pages: Int = 0,
)