package com.modulo10.juandev.mrsunshisjournal.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.application.MrSunshisJournalApp
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.HabitatAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.MascotaAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.journal.JournalViewModel
import com.modulo10.juandev.mrsunshisjournal.utils.message
import com.modulo10.juandev.mrsunshisjournal.utils.permisos.PermissionManager
import com.modulo10.juandev.mrsunshisjournal.utils.permisos.PermissionViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var journalRepository: JournalRepository
    private lateinit var homeViewModel: HomeViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inicializamos el repositorio de Journal
        val appContext = requireActivity().applicationContext as MrSunshisJournalApp
        journalRepository = appContext.repository

        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Crear el ViewModel usando el ViewModelFactory
        val viewModelFactory = HomeViewModelFactory(journalRepository)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


        // Inicializamos el PermissionViewModel
        //permissionViewModel = ViewModelProvider(this).get(PermissionViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        /*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
         */




/*
        // En tu Fragment o Activity

        val recyclerViewHorizontal = view.findViewById<RecyclerView>(R.id.recyclerViewHorizontal)
        val recyclerViewVertical = view.findViewById<RecyclerView>(R.id.recyclerViewVertical)

        // Configuración para RecyclerView Horizontal
        val horizontalAdapter = HorizontalAdapter()  // Tu Adapter para los elementos horizontales
        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHorizontal.layoutManager = horizontalLayoutManager
        recyclerViewHorizontal.adapter = horizontalAdapter

        // Configuración para RecyclerView Vertical
        val verticalAdapter = VerticalAdapter()  // Tu Adapter para los elementos verticales
        val verticalLayoutManager = LinearLayoutManager(context)
        recyclerViewVertical.layoutManager = verticalLayoutManager
        recyclerViewVertical.adapter = verticalAdapter


        
 */





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



    private fun loadHabitats(){
        homeViewModel.getAllHabitats()

        homeViewModel.habitatFiles.observe(viewLifecycleOwner){ habitats ->
            if (habitats.isNotEmpty()){
                val habitatAdapter = HabitatAdapter(habitats){
                    //Manejamos el click
                    message("Click habitat")
                }
                binding.rvHabitats.apply {
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    adapter = habitatAdapter
                }
            }

        }

    }

    private fun loadMascotas(){
        homeViewModel.getAllMascotas()

        homeViewModel.mascotaFiles.observe(viewLifecycleOwner){ mascotas ->
            if (mascotas.isNotEmpty()){
                val mascotaAdapter = MascotaAdapter(mascotas){
                    //Manejamos el click
                    message("Click habitat")
                }
                binding.rvMascotas.apply {
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    adapter = mascotaAdapter
                }
            }

        }

    }

}
