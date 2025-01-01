package com.modulo10.juandev.mrsunshisjournal.utils.permisos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PermissionViewModel : ViewModel() {

    //Cola para los strings de los permisos a solicitar
    private val permissionsToRequestQueue = mutableListOf<String>()

    //Ponemos los livedatas o elementos observables
    //Livedata para la lista de permisos a observar
    private val _permissionsToRequest = MutableLiveData<MutableList<String>>()
    val permissionsToRequest: LiveData<MutableList<String>> = _permissionsToRequest



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

    //asdasdasdasdasd

    // Añadir permisos a la cola y notificar a los observadores
    fun addPermissionToQueue(permission: String) {
        if (!permissionsToRequestQueue.contains(permission)) {
            permissionsToRequestQueue.add(permission)
            _permissionsToRequest.postValue(permissionsToRequestQueue)
        }
    }

    // Marcar un permiso como concedido
    fun onPermissionGranted(permission: String) {
        permissionsToRequestQueue.remove(permission)
        _permissionsToRequest.postValue(permissionsToRequestQueue)
    }

    // Marcar un permiso como denegado
    fun onPermissionDenied(permission: String) {
        if (!permissionsToRequestQueue.contains(permission)) {
            permissionsToRequestQueue.add(permission)
            _permissionsToRequest.postValue(permissionsToRequestQueue)
        }
    }

    // Obtener todos los permisos solicitados
    fun getPermissionsToRequest(): List<String> {
        return permissionsToRequestQueue
    }


}
