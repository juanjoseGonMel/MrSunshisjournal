package com.modulo10.juandev.mrsunshisjournal.data

import com.modulo10.juandev.mrsunshisjournal.data.db.ActividadDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.HabitatDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.MascotaDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity


class JournalRepository(
    private val habitatDao: HabitatDAO,
    private val mascotaDao: MascotaDAO,
    private val actividadDao: ActividadDAO
){

    // Métodos para Habitat

    suspend fun insertHabitat(habitat: HabitatEntity) {
        habitatDao.insertHabitat(habitat)
    }

    /*
    suspend fun insertarHabitats(habitats: MutableList<HabitatEntity>) {
        habitatDao.insertHabitats(habitats.toMutableList())
    }
    */

    suspend fun getAllHabitats(): MutableList<HabitatEntity> {
        return habitatDao.getAllHabitats()
    }

    suspend fun updateHabitat(habitat: HabitatEntity) {
        habitatDao.updateHabitat(habitat)
    }

    suspend fun delateHabitat(habitat: HabitatEntity) {
        habitatDao.delateHabitat(habitat)
    }


    // Métodos para Mascota

    suspend fun insertMascota(mascota: MascotaEntity) {
        mascotaDao.insertMascota(mascota)
    }

    /*
    suspend fun insertMascotas(mascotas: MutableList<HabitatEntity>) {
        mascotaDao.insertMascotas(mascotas)
    }
    */

    suspend fun getAllMascotas(): MutableList<MascotaEntity> {
        return mascotaDao.getAllMascotas()
    }

    suspend fun updateMascota(mascota: MascotaEntity) {
        mascotaDao.updateMascota(mascota)
    }

    suspend fun delateMascota(mascota: MascotaEntity) {
        mascotaDao.delateMascota(mascota)
    }


    // Métodos para Actividad

    suspend fun insertActividad(act: ActividadEntity) {
        actividadDao.insertActividad(act)
    }

    /*
    suspend fun insertActividades(acts: MutableList<ActividadEntity>) {
        actividadDao.insertActividades(acts)
    }
    */

    suspend fun getAllActividades(): MutableList<ActividadEntity> {
        return actividadDao.getAllActividades()
    }

    suspend fun updateActividad(act: ActividadEntity) {
        actividadDao.updateActividad(act)
    }

    suspend fun delateActividad(act: ActividadEntity) {
        actividadDao.delateActividad(act)
    }

}
