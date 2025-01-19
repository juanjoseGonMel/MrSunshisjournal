package com.modulo10.juandev.mrsunshisjournal.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.DialogPetBinding
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewMascotaListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateMascotaListener
import java.util.Date

class PetDialogFragment : DialogFragment() {

    private var _binding: DialogPetBinding? = null
    private val binding get() = _binding!!

    /*
    private val esterilList = arrayListOf("Estéril", "No esterilizado")
    private val razasList = arrayListOf("Chihuahua", "Pitbull", "Doberman", "Pug")
    private val generoList = arrayListOf("Macho", "Hembra")
    */

    //Corregir contexto
    private lateinit var esterilList: Array<String>
    private lateinit var razasList: Array<String>
    private lateinit var generoList: Array<String>


    private lateinit var habitatsList: List<HabitatEntity>

    private lateinit var esterilAdapter: ArrayAdapter<String>
    private lateinit var razasAdapter: ArrayAdapter<String>
    private lateinit var generoAdapter: ArrayAdapter<String>
    private lateinit var habitatsAdapter: ArrayAdapter<HabitatEntity>

    private lateinit var newMascotaListener: NewMascotaListener
    private lateinit var updateMascotaListener: UpdateMascotaListener

    private var savedMascota: MascotaEntity? = null
    private var isUpdate = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = DialogPetBinding.inflate(inflater, container, false)



        esterilList = resources.getStringArray(R.array.esteril_options)
        razasList = resources.getStringArray(R.array.razas_options)
        generoList = resources.getStringArray(R.array.genero_options)


        initDialog()

        return binding.root
    }

    fun setNewMascotaListener(newMascotaListener: NewMascotaListener) {
        this.newMascotaListener = newMascotaListener
    }

    fun setUpdateMascotaListener(updateMascotaListener: UpdateMascotaListener) {
        this.updateMascotaListener = updateMascotaListener
    }

    private fun initDialog() {
        context?.let { myContext ->
            esterilAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, esterilList).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            razasAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, razasList).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            generoAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, generoList).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

            binding.esterilSpin.adapter = esterilAdapter
            binding.razaSpin.adapter = razasAdapter
            binding.genreSpin.adapter = generoAdapter

            arguments?.let { params ->
                if (params.containsKey("habitats")) {
                    habitatsList = params.getParcelableArrayList<HabitatEntity>("habitats") ?: emptyList()
                }
                if (habitatsList != null && habitatsList.isNotEmpty()) {
                    habitatsAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, habitatsList).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                    binding.habitatsSpn.adapter = habitatsAdapter
                }

                isUpdate = params.getBoolean("update", false)

                if (isUpdate) {
                    savedMascota = params.getParcelable<MascotaEntity>("pet")

                    binding.saveMascotaBtn.setText("Actualizar Mascota")

                    loadMascota()
                }
            }
        }

        binding.saveMascotaBtn.setOnClickListener {
            val mascotaName = binding.petNameEdt.text.toString().trim()

            val mascotaPhoto = ""

            var selectedHabitat = binding.habitatsSpn.selectedItem as HabitatEntity
            var habitatId = if (selectedHabitat != null) selectedHabitat.id else 0

            val mascotaDesc = binding.petDescEdt.text.toString().trim()

            val mascotaWeightString = binding.petWeightEdt.text.toString().trim()
            var mascotaWeight = if (mascotaWeightString.isNullOrEmpty()) 0.0 else mascotaWeightString.toDouble()

            val mascotaBirhtdayString = binding.petBirthdayEdt.text.toString().trim()
            val date = Date()

            val mascotaEsterilString = binding.esterilSpin.selectedItem.toString()
            val mascotaEsteril = mascotaEsterilString == "Estéril"

            val mascotaRaza = binding.razaSpin.selectedItem.toString()

            val mascotaGenero = binding.genreSpin.selectedItem.toString()

            val newMascota = MascotaEntity(
                name = mascotaName,
                photo = mascotaPhoto,
                descripcion = mascotaDesc,
                pesoactual = mascotaWeight,
                cumple = date,
                esteril = mascotaEsteril,
                raza = mascotaRaza,
                genero = mascotaGenero,
                habitatId = habitatId
            )

            if (isUpdate) {
                updateMascotaListener.onUpdateMascota(newMascota)
            }
            else {
                newMascotaListener.onNewMascota(newMascota)
            }

            dismiss()
        }
    }

    private fun loadMascota() {
        savedMascota?.let { mascota ->
            binding.petNameEdt.setText(mascota.name)
            binding.petDescEdt.setText(mascota.descripcion)
            binding.petWeightEdt.setText(mascota.pesoactual.toString())
            binding.petBirthdayEdt.setText(mascota.cumple.toString())

            var razaPosition = 0
            for (i in 0 .. razasList.size) {
                if (razasList[i] == mascota.raza) {
                    razaPosition = i
                    break
                }
            }
            binding.razaSpin.setSelection(razaPosition)

            var habitatPosition = 0
            for (i in 0 .. habitatsList.size) {
                if (habitatsList[i].id == mascota.habitatId) {
                    habitatPosition = i
                    break
                }
            }
            binding.habitatsSpn.setSelection(habitatPosition)

            var esterilPosition = if (mascota.esteril) 0 else 1
            binding.esterilSpin.setSelection(esterilPosition)

            var generoPosition = if (mascota.genero == "Macho") 0 else 1
            binding.genreSpin.setSelection(generoPosition)
        }
    }
}