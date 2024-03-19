package cat.lasalle.chaplog.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookLogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookLogDao(): BookLogDao
}