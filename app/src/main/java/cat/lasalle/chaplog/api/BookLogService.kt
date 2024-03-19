package cat.lasalle.chaplog.api

import cat.lasalle.chaplog.data.BookLog
import retrofit2.http.GET

interface BookLogService {
    @GET("/books")
    suspend fun getBookLogs(): List<BookLog>
}