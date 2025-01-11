package com.modulo10.juandev.mrsunshisjournal.application

import android.app.Application
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MrSunshisJournalApp : Application() {

    /*
    private  val database by lazy {
        MrSunshisJournalDatabase.getDatabase(this@MrSunshisJournalApp)
    }
    val repository by lazy {
        JournalRepository(
            database.habitatDao(),
            database.mascotaDao(),
            database.actividadDao(),
            database.notificacionDao()
        )
    }
    */
}