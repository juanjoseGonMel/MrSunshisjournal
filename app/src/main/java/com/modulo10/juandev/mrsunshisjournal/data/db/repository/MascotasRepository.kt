package com.modulo10.juandev.mrsunshisjournal.data.db.repository

import android.content.Context
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.MascotaDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity

class MascotasRepository private constructor(private val dao: MascotaDAO) {

    companion object {

        @Volatile
        private var instance: MascotasRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MascotasRepository(MrSunshisJournalDatabase.getDatabase(context).mascotaDao()).also {
                    instance = it
                }
            }
    }

    suspend fun insertMascota(mascota: MascotaEntity) : Long {
        return dao.insertMascota(mascota)
    }

    /*
    suspend fun insertMascotas(mascotas: MutableList<HabitatEntity>) {
        mascotaDao.insertMascotas(mascotas)
    }
    */

    suspend fun getAllMascotas(): MutableList<MascotaEntity> {
        return dao.getAllMascotas()
    }

    suspend fun updateMascota(mascota: MascotaEntity) {
        dao.updateMascota(mascota)
    }

    suspend fun delateMascota(mascota: MascotaEntity) {
        dao.delateMascota(mascota)
    }
}