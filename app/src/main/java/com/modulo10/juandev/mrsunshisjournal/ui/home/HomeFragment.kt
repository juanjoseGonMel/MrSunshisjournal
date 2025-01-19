package com.modulo10.juandev.mrsunshisjournal.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.application.MrSunshisJournalApp
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.HabitatAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.MascotaAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.common.BaseFragment
import com.modulo10.juandev.mrsunshisjournal.ui.dialogs.HabitatDialogFragment
import com.modulo10.juandev.mrsunshisjournal.ui.dialogs.PetDialogFragment
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewMascotaListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateMascotaListener
import com.modulo10.juandev.mrsunshisjournal.utils.message
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var journalRepository: JournalRepository
    private val homeViewModel: HomeViewModel by viewModels()
    //private val viewModel : PayServicesViewModel by viewModels()

    private var habitatsList = arrayListOf<HabitatEntity>()
    private var mascotasList: List<MascotaEntity> = arrayListOf()

    private lateinit var createMascotaListener: NewMascotaListener
    private lateinit var createHabitatListener: NewHabitatListener

    private lateinit var updateMascotaListener: UpdateMascotaListener
    private lateinit var updateHabitatListener: UpdateHabitatListener

    //private lateinit var permissionViewModel: PermissionViewModel
    //private lateinit var permissionManager: PermissionManager

    /*
    private val musicListViewModel : MusicListViewModel by viewModels{
        MusicListViewModelFactory(AudioRepository(requireContext()))
    }
    private val homeViewModel: HomeViewModel by viewModels(
        HomeViewModelFactory(JournalRepository())
    )
    */

    // Definimos los permisos que vamos a utilizar
    /*
    private val permissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        permissionManager.handlePermissionsResult(permissionsMap)
    }
     */

    //private lateinit var habitatAdapter: HabitatAdapter
    //private var readMediaAudioGranted = false //Read Media Audio
    //private var readPermissionGranted = false //Read External Storage
    //private var writePermissionGranted = false //Write External Storage
    //private var permissionsToRequest = mutableListOf<String>()

    /*
    private var permissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){ permissionsMap : Map<String, Boolean> ->
        val allGranted = permissionsMap.all { map ->
            map.value
        }
        if (allGranted){
            actionPermissionGranted()
        }else{

            permissionsToRequest.forEach { permission ->
                musicListViewModel.onPermissionResult(
                    permission = permission,
                    isGranted = permissionsMap[permission] == true
                )
            }
        }
    }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inicializamos el repositorio de Journal
        val appContext = requireActivity().applicationContext as MrSunshisJournalApp
        //journalRepository = appContext.repository

        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Crear el ViewModel usando el ViewModelFactory
        //val viewModelFactory = HomeViewModelFactory(journalRepository)
        //homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpListeners()

        return root
    }

    override fun onStart() {
        super.onStart()
        loadHabitats()
        loadMascotas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpListeners() {
        binding.fabNewHabitat.setOnClickListener {
            showNewHabitatForm()
        }

        binding.fabNewPet.setOnClickListener {
            showNewPetForm()
        }

        createHabitatListener = object : NewHabitatListener {
            override fun onNewHabitat(habitat: HabitatEntity) {
                saveHabitat(habitat)
            }

        }

        createMascotaListener = object : NewMascotaListener {
            override fun onNewMascota(mascota: MascotaEntity) {
                saveMascota(mascota)
            }
        }

        updateHabitatListener = object : UpdateHabitatListener {
            override fun onUpdateHabitat(habitat: HabitatEntity) {
                updateHabitat(habitat)
            }

        }

        updateMascotaListener = object : UpdateMascotaListener {
            override fun  onUpdateMascota(mascota: MascotaEntity) {
                updateMascota(mascota)
            }
        }
    }

    private fun setUpObservers() {
        homeViewModel.saveMascotaResponse.observe(this) { result ->
            if (result) {
                showAlert("Nueva Mascota", "Se guardó la mascota correctamente", "Aceptar")
                loadMascotas()
            }
            else {
                showAlert("Error", "Hubo un error al guardar la mascota. Reintente por favor", "Aceptar")
            }
        }

        homeViewModel.saveHabitatResponse.observe(this) { result ->
            if (result) {
                showAlert("Nuevo Hábitat", "Se guardó el hábitat correctamente", "Aceptar")
                loadHabitats()
            }
            else {
                showAlert("Error", "Hubo un error al guardar el hábitat. Reintente por favor", "Aceptar")
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

    private fun loadMascotas() {
        showLoadingMascotas()

        homeViewModel.getAllMascotas()

        homeViewModel.mascotaFiles.observe(viewLifecycleOwner) { mascotas ->
            if (mascotas.isNotEmpty()) {
                mascotasList = mascotas
                val mascotaAdapter = MascotaAdapter(mascotas) { mascota ->
                    openMascotaDetail(mascota)
                }
                binding.rvMascotas.apply {
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    adapter = mascotaAdapter
                }
                showMascotas()
            }
            else {
                showEmptyMascotas()
            }
        }
    }

    private fun showLoadingMascotas() {
        binding.rvMascotas.visibility = View.GONE
        binding.emptyPetsLyt.visibility = View.GONE
        binding.loadingPetLyt.visibility = View.VISIBLE
    }

    private fun showLoadingHabitats() {
        binding.rvHabitats.visibility = View.GONE
        binding.emptyHabitatLyt.visibility = View.GONE
        binding.loadingHabitatLyt.visibility = View.VISIBLE
    }

    private fun showEmptyMascotas() {
        binding.rvMascotas.visibility = View.GONE
        binding.emptyPetsLyt.visibility = View.VISIBLE
        binding.loadingPetLyt.visibility = View.GONE
    }

    private fun showEmptyHabitats() {
        binding.rvHabitats.visibility = View.GONE
        binding.emptyHabitatLyt.visibility = View.VISIBLE
        binding.loadingHabitatLyt.visibility = View.GONE
    }

    private fun showMascotas() {
        binding.rvMascotas.visibility = View.VISIBLE
        binding.emptyPetsLyt.visibility = View.GONE
        binding.loadingPetLyt.visibility = View.GONE
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
}
