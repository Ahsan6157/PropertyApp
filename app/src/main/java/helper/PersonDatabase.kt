package helper
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import models.Person
import models.PropertyAddress

@Database(entities = [Person::class,PropertyAddress::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun propertyAddDo():PropertyAddDao
    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null
        fun getInstance(context: Context): PersonDatabase {
            if (INSTANCE == null) {
        synchronized(this){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext, PersonDatabase::class.java, "personDatabase").fallbackToDestructiveMigration().build()
        }
            }
            return INSTANCE!!
        }

    }
}
