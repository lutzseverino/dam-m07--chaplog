package cat.lasalle.chaplog.api

import cat.lasalle.chaplog.data.BookLog
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookLogService {
    @GET("book_logs")
    suspend fun getBookLogs(): List<BookLog>

    @PUT("book_logs/{id}")
    suspend fun updateBookLog(@Path("id") id: Int, @Body bookLog: BookLog): BookLog
}