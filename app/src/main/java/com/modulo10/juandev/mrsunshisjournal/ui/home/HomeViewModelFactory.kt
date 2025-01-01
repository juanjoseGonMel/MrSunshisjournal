package com.modulo10.juandev.mrsunshisjournal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository


class HomeViewModelFactory(
    private val journalRepository: JournalRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(journalRepository) as T
        }
        throw IllegalArgumentException("Clase viewmodel desconocida")
    }
}
