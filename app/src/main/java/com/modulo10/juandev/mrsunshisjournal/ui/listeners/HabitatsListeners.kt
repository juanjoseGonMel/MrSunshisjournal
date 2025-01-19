package com.modulo10.juandev.mrsunshisjournal.ui.listeners

import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity

interface NewHabitatListener {
    fun onNewHabitat(habitat: HabitatEntity)
}

interface UpdateHabitatListener {
    fun onUpdateHabitat(habitat: HabitatEntity)
}