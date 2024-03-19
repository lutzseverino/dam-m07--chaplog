package cat.lasalle.chaplog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookLogDao {
    @Query("SELECT * FROM book_logs")
    suspend fun getAll(): List<BookLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bookLogs: List<BookLogEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(bookLog: BookLogEntity)
}