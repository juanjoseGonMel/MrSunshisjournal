package com.modulo10.juandev.mrsunshisjournal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val journalRepository: JournalRepository
) : ViewModel() {

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


    fun getAllHabitats(){
        viewModelScope.launch(Dispatchers.IO) {
            _habitatFiles.postValue(journalRepository.getAllHabitats())
        }
    }

    fun getAllMascotas(){
        viewModelScope.launch(Dispatchers.IO) {
            _mascotaFiles.postValue(journalRepository.getAllMascotas())
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
