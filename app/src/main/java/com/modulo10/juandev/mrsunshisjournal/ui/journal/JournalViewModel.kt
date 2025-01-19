package com.modulo10.juandev.mrsunshisjournal.ui.journal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.repository.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JournalViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val actividadRepository = EventsRepository.getInstance(context)


    //Livedata para la lista
    private val _actFiles = MutableLiveData<MutableList<ActividadEntity>>()
    val actFiles: LiveData<MutableList<ActividadEntity>> = _actFiles


    private val _saveActResponse = MutableLiveData<Boolean>()
    val saveActResponse: LiveData<Boolean> = _saveActResponse



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