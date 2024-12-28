package com.modulo10.juandev.mrsunshisjournal.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.utils.Constants


@Dao
interface HabitatDAO {

    //Create
    @Insert
    suspend fun insertHabitat(habitat: HabitatEntity)
    @Insert
    suspend fun insertHabitats(habitats: MutableList<HabitatEntity>)

    //Read
    @Query("SELECT * FROM ${Constants.DATABASE_HABITAT_TABLE}")
    suspend fun getAllHabitats():MutableList<HabitatEntity>
    
    //@Query("SELECT * FROM habitats")
    //suspend fun getAllHabitats2(): List<HabitatEntity>
    
    //Update
    @Update
    suspend fun updateHabitat(habitat: HabitatEntity)
    
    //Delete
    @Delete
    suspend fun delateHabitat(habitat: HabitatEntity)
}