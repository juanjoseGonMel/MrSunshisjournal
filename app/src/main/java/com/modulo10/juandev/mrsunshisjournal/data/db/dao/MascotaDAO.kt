package com.modulo10.juandev.mrsunshisjournal.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.utils.Constants


@Dao
interface MascotaDAO {

    //Create
    @Insert
    suspend fun insertMascota(mascota: MascotaEntity): Long
    @Insert
    suspend fun insertMascotas(mascotas: MutableList<MascotaEntity>)



    //Read
    @Query("SELECT * FROM ${Constants.DATABASE_MASCOTA_TABLE}")
    suspend fun getAllMascotas():MutableList<MascotaEntity>

    //@Query("SELECT * FROM habitats")
    //suspend fun getAllMascotas2(): List<HabitatEntity>


    //Update
    @Update
    suspend fun updateMascota(mascota: MascotaEntity)

    //Delete
    @Delete
    suspend fun delateMascota(mascota: MascotaEntity)

}