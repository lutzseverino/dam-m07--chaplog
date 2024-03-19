package cat.lasalle.chaplog.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_logs")
data class BookLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val currentPage: Int = 0,
    val pages: Int = 0,
)