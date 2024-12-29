package com.modulo10.juandev.mrsunshisjournal.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity
import com.modulo10.juandev.mrsunshisjournal.utils.Constants


@Dao
interface NotificationsDAO {

    //Create
    @Insert
    suspend fun insertNotificacion(notificacion: NotificationsEntity)
    @Insert
    suspend fun insertNotificaciones(notificacion: MutableList<NotificationsEntity>)

    //Read
    @Transaction
    @Query("SELECT * FROM ${Constants.DATABASE_NOTIFICATIONS_TABLE}")
    suspend fun getAllNotificaciones():MutableList<NotificationsEntity>

    //@Query("SELECT * FROM habitats")
    //suspend fun getAllActividades2(): List<HabitatEntity>

    //Update
    @Update
    suspend fun updateNotificacion(notificacion: NotificationsEntity)

    //Delete
    @Delete
    suspend fun delateNotificacion(notificacion: NotificationsEntity)
}