package com.modulo10.juandev.mrsunshisjournal.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.EventsRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.MascotasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val mascotasRepository = MascotasRepository.getInstance(context)

    private val _mascotasList = MutableLiveData<MutableList<MascotaEntity>>()
    val mascotasList: LiveData<MutableList<MascotaEntity>> = _mascotasList


    private val actividadRepository = EventsRepository.getInstance(context)

    //Livedata para la lista
    private val _actFiles = MutableLiveData<MutableList<ActividadEntity>>()
    val actFiles: LiveData<MutableList<ActividadEntity>> = _actFiles


    private val _saveActResponse = MutableLiveData<Boolean>()
    val saveActResponse: LiveData<Boolean> = _saveActResponse


    fun getAllMascotas() {
        viewModelScope.launch(Dispatchers.IO) {
            _mascotasList.postValue(mascotasRepository.getAllMascotas())
        }
    }


    fun getAllActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            _actFiles.postValue(actividadRepository.getAllActividades())
        }
    }


    fun saveActividad(act: ActividadEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = actividadRepository.insertActividad(act)

            _saveActResponse.postValue(result > 0)
        }
    }

}