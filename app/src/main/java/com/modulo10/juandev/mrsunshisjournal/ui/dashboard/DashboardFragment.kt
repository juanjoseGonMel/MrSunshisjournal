package com.modulo10.juandev.mrsunshisjournal.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentDashboardBinding
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.ui.home.HomeViewModel
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewActividadListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewMascotaListener

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!



    //private val dashboardViewModel: DashboardViewModel by viewModels()

    //private var actividadList = arrayListOf<ActividadEntity>()


    //private lateinit var createActividadListener: NewActividadListener



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}