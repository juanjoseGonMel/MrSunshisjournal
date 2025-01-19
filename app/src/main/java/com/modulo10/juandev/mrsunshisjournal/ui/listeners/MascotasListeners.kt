package com.modulo10.juandev.mrsunshisjournal.ui.listeners

import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity

interface NewMascotaListener {
    fun onNewMascota(mascota: MascotaEntity)
}

interface UpdateMascotaListener {
    fun onUpdateMascota(mascota: MascotaEntity)
}