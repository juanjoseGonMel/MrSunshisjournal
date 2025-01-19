package com.modulo10.juandev.mrsunshisjournal.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.DialogHabitatBinding
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener

class HabitatDialogFragment : DialogFragment() {

    private var _binding: DialogHabitatBinding? = null
    private val binding get() = _binding!!

    //private val openCloseList = arrayListOf("Abierto", "Cerrado")
    private lateinit var openCloseList: Array<String>

    private lateinit var newHabitatListener: NewHabitatListener

    private lateinit var openCloseAdapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = DialogHabitatBinding.inflate(inflater, container, false)

        openCloseList = resources.getStringArray(R.array.openclose_options)

        initDialog()

        return binding.root
    }

    fun setNewHabitatListener(newHabitatListener: NewHabitatListener) {
        this.newHabitatListener = newHabitatListener
    }

    private fun initDialog() {
        context?.let { myContext ->
            openCloseAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, openCloseList).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.openCloseSpin.adapter = openCloseAdapter
        }

        binding.saveHabitatBtn.setOnClickListener {
            val habitatName = binding.habitatNameEdt.text.toString().trim()

            val habitatPhoto = ""

            val habitatDesc = binding.habitatDescEdt.text.toString().trim()

            val habitatCapacityString = binding.habitatCapacityEdt.text.toString().trim()
            var habitatCapacity = if (habitatCapacityString.isNullOrEmpty()) 0 else habitatCapacityString.toInt()

            val habitatSizeString = binding.habitatSizeEdt.text.toString().trim()
            var habitatSize = if (habitatSizeString.isNullOrEmpty()) 0.0 else habitatSizeString.toDouble()

            val habitatTempString = binding.habitatTempEdt.text.toString().trim()
            var habitatTemp = if (habitatTempString.isNullOrEmpty()) 0.0 else habitatTempString.toDouble()

            val habitatType = binding.habitatTypeEdt.text.toString().trim()

            val openCloseString = binding.openCloseSpin.selectedItem.toString()
            val open = openCloseString == "Abierto"

            val newHabitat = HabitatEntity(
                name = habitatName,
                descripcion = habitatDesc,
                photo = habitatPhoto,
                capacidad = habitatCapacity,
                size = habitatSize,
                temperatura = habitatTemp,
                tipo = habitatType,
                abierta = open
            )

            newHabitatListener.onNewHabitat(newHabitat)
            dismiss()
        }
    }

}