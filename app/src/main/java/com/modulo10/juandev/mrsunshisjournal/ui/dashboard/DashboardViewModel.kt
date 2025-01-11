package com.modulo10.juandev.mrsunshisjournal.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.ActividadRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.HabitatsRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.MascotasRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val alarmasRepository = ActividadRepository.getInstance(context)
    private val notificacionesRepository = NotificationsRepository.getInstance(context)


    //Livedata para la lista
    private val _alarmasFiles = MutableLiveData<MutableList<ActividadEntity>>()
    val alarmasFiles: LiveData<MutableList<ActividadEntity>> = _alarmasFiles

    private val _notificacionesFiles = MutableLiveData<MutableList<NotificationsEntity>>()
    val notificacionesFiles: LiveData<MutableList<NotificationsEntity>> = _notificacionesFiles

    private val _saveAlarmasResponse = MutableLiveData<Boolean>()
    val saveAlarmasResponse: LiveData<Boolean> = _saveAlarmasResponse

    private val _saveNotificacionesResponse = MutableLiveData<Boolean>()
    val saveNotificacionesResponse: LiveData<Boolean> = _saveNotificacionesResponse





    fun getAllActividades() {
        viewModelScope.launch(Dispatchers.IO) {
            _alarmasFiles.postValue(alarmasRepository.getAllActividades())
        }
    }

    fun getAllNotificaciones() {
        viewModelScope.launch(Dispatchers.IO) {
            _notificacionesFiles.postValue(notificacionesRepository.getAllNotificaciones())
        }
    }

    fun saveAlarma(act: ActividadEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = alarmasRepository.insertActividad(act)

            _saveAlarmasResponse.postValue(result > 0)
        }
    }

    fun saveNotificacion(noti: NotificationsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = notificacionesRepository.insertNotificacion(noti)

            _saveNotificacionesResponse.postValue(result > 0)
        }
    }


}
