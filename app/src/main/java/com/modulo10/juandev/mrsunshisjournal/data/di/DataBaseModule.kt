package com.modulo10.juandev.mrsunshisjournal.data.di

import android.content.Context
import com.modulo10.juandev.mrsunshisjournal.data.db.MrSunshisJournalDatabase
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.HabitatDAO
import com.modulo10.juandev.mrsunshisjournal.data.db.dao.MascotaDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides //TODO Add migrations
    fun provideAppDataBase(@ApplicationContext context: Context): MrSunshisJournalDatabase {
        return MrSunshisJournalDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMascotaDao(appDatabase: MrSunshisJournalDatabase): MascotaDAO {
        return appDatabase.mascotaDao()
    }

    @Singleton
    @Provides
    fun provideHabitatDao(appDatabase: MrSunshisJournalDatabase): HabitatDAO = appDatabase.habitatDao()

}