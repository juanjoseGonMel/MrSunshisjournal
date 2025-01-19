package com.modulo10.juandev.mrsunshisjournal.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.utils.Constants


@Dao
interface ActividadDAO {

    //Create

    @Insert
    suspend fun insertActividad(actividad: ActividadEntity) : Long
    @Insert
    suspend fun insertActividades(actividades: MutableList<ActividadEntity>)

    //Read
    @Query("SELECT * FROM ${Constants.DATABASE_ACTIVIDAD_TABLE}")
    suspend fun getAllActividades():MutableList<ActividadEntity>

    //@Query("SELECT * FROM habitats")
    //suspend fun getAllActividades2(): List<HabitatEntity>

    //Update
    @Update
    suspend fun updateActividad(actividad: ActividadEntity)

    //Delete
    @Delete
    suspend fun delateActividad(actividad: ActividadEntity)
}