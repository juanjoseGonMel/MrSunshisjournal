package com.modulo10.juandev.mrsunshisjournal.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.HabitatsRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.MascotasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context) : ViewModel() {

    private val mascotasRepository = MascotasRepository.getInstance(context)
    private val habitatsRepository = HabitatsRepository.getInstance(context)

    /*
    //Cola para los strings de los permisos a solicitar
    private val permissionsToRequestQueue = mutableListOf<String>()

    //Ponemos los livedatas o elementos observables

    //Livedata para la lista de permisos a observar
    private val _permissionsToRequest = MutableLiveData<MutableList<String>>()
    val permissionsToRequest: LiveData<MutableList<String>> = _permissionsToRequest
     */


    //Livedata para la lista
    private val _habitatFiles = MutableLiveData<MutableList<HabitatEntity>>()
    val habitatFiles: LiveData<MutableList<HabitatEntity>> = _habitatFiles

    private val _mascotaFiles = MutableLiveData<MutableList<MascotaEntity>>()
    val mascotaFiles: LiveData<MutableList<MascotaEntity>> = _mascotaFiles

    private val _saveMascotaResponse = MutableLiveData<Boolean>()
    val saveMascotaResponse: LiveData<Boolean> = _saveMascotaResponse

    private val _saveHabitatResponse = MutableLiveData<Boolean>()
    val saveHabitatResponse: LiveData<Boolean> = _saveHabitatResponse


    fun getAllHabitats() {
        viewModelScope.launch(Dispatchers.IO) {
            _habitatFiles.postValue(habitatsRepository.getAllHabitats())
        }
    }

    fun getAllMascotas() {
        viewModelScope.launch(Dispatchers.IO) {
            _mascotaFiles.postValue(mascotasRepository.getAllMascotas())
        }
    }

    fun saveMascota(mascota: MascotaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mascotasRepository.insertMascota(mascota)

            _saveMascotaResponse.postValue(result > 0)
        }
    }

    fun saveHabitat(habitat: HabitatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = habitatsRepository.insertHabitat(habitat)

            _saveHabitatResponse.postValue(result > 0)
        }
    }

    /*
    //Función para quitar los permisos de la cola
    fun dismissDialogRemovePermission(){
        if(permissionsToRequestQueue.isNotEmpty())
            permissionsToRequestQueue.removeAt(0) //Quita el primer elemento de cola
    }

    //Para manejar el resultado del permiso
    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        if(!isGranted && !permissionsToRequestQueue.contains(permission)) {
            permissionsToRequestQueue.add(permission)
            _permissionsToRequest.postValue(permissionsToRequestQueue)
        }

    }
    */

}
