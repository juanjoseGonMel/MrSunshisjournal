package com.modulo10.juandev.mrsunshisjournal.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import com.modulo10.juandev.mrsunshisjournal.utils.Converters



@Database(
    entities = [HabitatEntity::class, MascotaEntity::class, ActividadEntity::class, NotificationsEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)  // Registra el TypeConverter
abstract class MrSunshisJournalDatabase : RoomDatabase() {

    abstract fun habitatDao(): HabitatDAO
    abstract fun mascotaDao(): MascotaDAO
    abstract fun actividadDao(): ActividadDAO
    abstract fun notificacionDao(): NotificationsDAO


    companion object{
        @Volatile
        private var INSTANCE: MrSunshisJournalDatabase? = null

        fun getDatabase(context: Context):MrSunshisJournalDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MrSunshisJournalDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }


}
