package com.modulo10.juandev.mrsunshisjournal.data.db.repository

import android.content.Context
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.HabitatDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity

class HabitatsRepository private constructor(private val dao: HabitatDAO) {

    companion object {

        @Volatile
        private var instance: HabitatsRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: HabitatsRepository(MrSunshisJournalDatabase.getDatabase(context).habitatDao()).also {
                    instance = it
                }
            }
    }

    suspend fun insertHabitat(habitat: HabitatEntity) : Long {
        return dao.insertHabitat(habitat)
    }

    /*
    suspend fun insertarHabitats(habitats: MutableList<HabitatEntity>) {
        habitatDao.insertHabitats(habitats.toMutableList())
    }
    */

    suspend fun getAllHabitats(): MutableList<HabitatEntity> {
        return dao.getAllHabitats()
    }

    suspend fun updateHabitat(habitat: HabitatEntity) {
        dao.updateHabitat(habitat)
    }

    suspend fun delateHabitat(habitat: HabitatEntity) {
        dao.delateHabitat(habitat)
    }

}