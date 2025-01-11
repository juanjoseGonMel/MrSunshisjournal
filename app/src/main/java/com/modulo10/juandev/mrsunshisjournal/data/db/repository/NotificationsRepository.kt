package com.modulo10.juandev.mrsunshisjournal.data.db.repository

import android.content.Context
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.NotificationsDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity

class NotificationsRepository private constructor(private val dao: NotificationsDAO) {

    companion object {

        @Volatile
        private var instance: NotificationsRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NotificationsRepository(MrSunshisJournalDatabase.getDatabase(context).notificacionDao()).also {
                    instance = it
                }
            }
    }

    suspend fun insertNotificacion(noti: NotificationsEntity) : Long{
        return dao.insertNotificacion(noti)
    }

    /*
    suspend fun insertarHabitats(habitats: MutableList<HabitatEntity>) {
        habitatDao.insertHabitats(habitats.toMutableList())
    }
    */

    suspend fun getAllNotificaciones(): MutableList<NotificationsEntity> {
        return dao.getAllNotificaciones()
    }

    suspend fun updateNotificacion(noti: NotificationsEntity) {
        dao.updateNotificacion(noti)
    }

    suspend fun delateNotificacion(noti: NotificationsEntity) {
        dao.delateNotificacion(noti)
    }

}
