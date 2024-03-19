package cat.lasalle.chaplog.data

data class BookLogList(
    val focusedLog : BookLog? = null,
    val logs : List<BookLog> = emptyList()
)