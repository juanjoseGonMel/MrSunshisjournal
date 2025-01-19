package com.modulo10.juandev.mrsunshisjournal.ui.journal

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.application.MrSunshisJournalApp
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentJournalBinding
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.HabitatAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.MascotaAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.common.BaseFragment
import com.modulo10.juandev.mrsunshisjournal.ui.dialogs.HabitatDialogFragment
import com.modulo10.juandev.mrsunshisjournal.ui.dialogs.PetDialogFragment
import com.modulo10.juandev.mrsunshisjournal.ui.home.HomeViewModel
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewActividadListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewMascotaListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateMascotaListener
import com.modulo10.juandev.mrsunshisjournal.utils.message
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JournalFragment : BaseFragment() {

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!



    //private val journalViewModel: JournalViewModel by viewModels()
    private val viewModel: JournalViewModel by viewModels()

    private var actividadList = arrayListOf<ActividadEntity>()

    private lateinit var createActividadListener: NewActividadListener

    //private lateinit var updateMascotaListener: UpdateMascotaListener




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inicializamos el repositorio de Journal
        val appContext = requireActivity().applicationContext as MrSunshisJournalApp


        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //setUpListeners()

        return root
    }

    override fun onStart() {
        super.onStart()

        //loadHabitats()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /*
    private fun setUpListeners() {
        binding.fabNewHabitat.setOnClickListener {
            showNewHabitatForm()
        }

        createHabitatListener = object : NewHabitatListener {
            override fun onNewHabitat(habitat: HabitatEntity) {
                saveHabitat(habitat)
            }

        }

        updateHabitatListener = object : UpdateHabitatListener {
            override fun onUpdateHabitat(habitat: HabitatEntity) {
                updateHabitat(habitat)
            }

        }


    }

    private fun setUpObservers() {

        viewModel.saveActResponse.observe(this) { result ->
            if (result) {
                showAlert("Nueva alarma", "Se guardó la alarma correctamente", "Aceptar")
                loadHabitats()
            }
            else {
                showAlert("Error", "Hubo un error al guardar la alarma. Reintente por favor", "Aceptar")
            }
        }

    }

    private fun loadHabitats() {
        showLoadingHabitats()

        homeViewModel.getAllHabitats()

        homeViewModel.habitatFiles.observe(viewLifecycleOwner) { habitats ->
            if (habitats.isNotEmpty()) {
                habitatsList = ArrayList(habitats)
                val habitatAdapter = HabitatAdapter(habitats) {
                    //Manejamos el click
                    message("Click habitat")
                }
                binding.rvHabitats.apply {
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    adapter = habitatAdapter
                }
                showHabitats()
            }
            else {
                showEmptyHabitats()
            }
        }

    }

    private fun showLoadingHabitats() {
        binding.rvHabitats.visibility = View.GONE
        binding.emptyHabitatLyt.visibility = View.GONE
        binding.loadingHabitatLyt.visibility = View.VISIBLE
    }

    private fun showEmptyHabitats() {
        binding.rvHabitats.visibility = View.GONE
        binding.emptyHabitatLyt.visibility = View.VISIBLE
        binding.loadingHabitatLyt.visibility = View.GONE
    }

    private fun showHabitats() {
        binding.rvHabitats.visibility = View.VISIBLE
        binding.emptyHabitatLyt.visibility = View.GONE
        binding.loadingHabitatLyt.visibility = View.GONE
    }

    private fun saveMascota(mascota: MascotaEntity) {
        homeViewModel.saveMascota(mascota)
    }

    private fun saveHabitat(habitat: HabitatEntity) {
        homeViewModel.saveHabitat(habitat)
    }

    private fun updateMascota(mascota: MascotaEntity) {

    }

    private fun updateHabitat(habitat: HabitatEntity) {

    }

    private fun showNewHabitatForm() {
        val habitatDialog = HabitatDialogFragment().apply {
            setNewHabitatListener(createHabitatListener)
        }
        activity?.let { myActivity ->
            habitatDialog.show(myActivity.supportFragmentManager, "HabitattDialog")
        }

        /*
        val mockHabitat = HabitatEntity(
            name = "Hábitat de Prueba",
            photo = "",
            descripcion = "Descripcion habitat",
            tipo = "Tipo habitat",
            size = 10.0,
            temperatura = 20.0
        )

        saveHabitat(mockHabitat)
        */
    }

    private fun showNewPetForm() {
        if (habitatsList != null && habitatsList.isNotEmpty()) {

            val params = Bundle()
            params.putParcelableArrayList("habitats", habitatsList)

            val petDialog = PetDialogFragment().apply {
                arguments = params
                setNewMascotaListener(createMascotaListener)
            }
            activity?.let { myActivity ->
                petDialog.show(myActivity.supportFragmentManager, "PetDialog")
            }
        }
        else {
            showAlert("Error",
                "Primero debes agregar un hábitat antes de agregar una mascota",
                "Aceptar",
                isCancelable = false)
        }
    }

    fun openMascotaDetail(mascota: MascotaEntity) {
        val params = Bundle()
        params.putParcelable("pet", mascota)
        params.putParcelableArrayList("habitats", habitatsList)
        params.putBoolean("update", true)

        val petDialog = PetDialogFragment().apply {
            arguments = params
            setNewMascotaListener(createMascotaListener)
        }
        activity?.let { myActivity ->
            petDialog.show(myActivity.supportFragmentManager, "PetDetailDialog")
        }
    }

     */

}