package com.modulo10.juandev.mrsunshisjournal.data.db.repository

import android.content.Context
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.ActividadDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity

class ActividadRepository private constructor(private val dao: ActividadDAO) {

    companion object {

        @Volatile
        private var instance: ActividadRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ActividadRepository(MrSunshisJournalDatabase.getDatabase(context).actividadDao()).also {
                    instance = it
                }
            }
    }

    suspend fun insertActividad(act: ActividadEntity):Long {
        return dao.insertActividad(act)
    }

    /*
    suspend fun insertarHabitats(habitats: MutableList<HabitatEntity>) {
        habitatDao.insertHabitats(habitats.toMutableList())
    }
    */

    suspend fun getAllActividades(): MutableList<ActividadEntity> {
        return dao.getAllActividades()
    }

    suspend fun updateActividad(act: ActividadEntity) {
        dao.updateActividad(act)
    }

    suspend fun delateActividad(act: ActividadEntity) {
        dao.delateActividad(act)
    }

}