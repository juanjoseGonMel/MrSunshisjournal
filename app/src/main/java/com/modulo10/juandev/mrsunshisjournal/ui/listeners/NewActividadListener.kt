package com.modulo10.juandev.mrsunshisjournal.ui.listeners

import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity

interface NewActividadListener {
    fun onNewActividad(act: ActividadEntity)
}