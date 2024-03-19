package cat.lasalle.chaplog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import cat.lasalle.chaplog.api.RetrofitInstance
import cat.lasalle.chaplog.database.AppDatabase
import cat.lasalle.chaplog.database.repository.BookLogRepository
import cat.lasalle.chaplog.ui.theme.Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bookLogService = RetrofitInstance.bookLogService
        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "booklog-database"
        ).build()

        val bookLogRepository = BookLogRepository(bookLogService, appDatabase)

        setContent {
            Theme {
                ChapLogApp(bookLogRepository = bookLogRepository)
            }
        }
    }

}