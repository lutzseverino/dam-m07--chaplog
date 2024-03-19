package cat.lasalle.chaplog.database.repository

import cat.lasalle.chaplog.api.BookLogService
import cat.lasalle.chaplog.database.AppDatabase
import cat.lasalle.chaplog.database.BookLogEntity

class BookLogRepository(private val service: BookLogService, private val db: AppDatabase) {
    suspend fun getBookLogs(): List<BookLogEntity> {
        val bookLogs = service.getBookLogs().map {
            BookLogEntity(it.id, it.title, it.author, it.currentPage, it.pages)
        }

        db.bookLogDao().insertAll(bookLogs)
        return db.bookLogDao().getAll()
    }
}