package com.modulo10.juandev.mrsunshisjournal.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity

import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentDashboardBinding
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.ActividadAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.adapters.HabitatAdapter
import com.modulo10.juandev.mrsunshisjournal.ui.common.BaseFragment
import com.modulo10.juandev.mrsunshisjournal.ui.dialogs.EventDialogFragment
import com.modulo10.juandev.mrsunshisjournal.ui.home.HomeViewModel
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewActividadListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewMascotaListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateHabitatListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.UpdateMascotaListener
import com.modulo10.juandev.mrsunshisjournal.utils.message
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var mascotasList= arrayListOf<MascotaEntity>()




    private var actividadesList = arrayListOf<ActividadEntity>()

    private lateinit var createActividadListener: NewActividadListener

    //private lateinit var updateMascotaListener: UpdateMascotaListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpObservers()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpListeners()

        return root
    }

    override fun onStart() {
        super.onStart()

        loadMascotas()
        loadActividades()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpListeners() {
        binding.calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            }
        })

        binding.fabNewEvent.setOnClickListener {
            showEventForm()
        }


        createActividadListener = object : NewActividadListener {
            override fun onNewActividad(act: ActividadEntity) {
                saveActividad(act)
            }
        }


    }

    private fun setUpObservers() {
        dashboardViewModel.saveActResponse.observe(this) { result ->
            if (result) {
                showAlert("Nueva Alarma", "Se guardó la alarma correctamente", "Aceptar")
                loadActividades()
            }
            else {
                showAlert("Error", "Hubo un error al guardar la alarma. Reintente por favor", "Aceptar")
            }
        }

    }

    private fun loadActividades() {
        showLoadingActividades()

        dashboardViewModel.getAllActivities()

        dashboardViewModel.actFiles.observe(viewLifecycleOwner) { acts ->
            if (acts.isNotEmpty()) {
                actividadesList = ArrayList(acts)
                val actividadAdapter = ActividadAdapter(acts) {
                    //Manejamos el click
                    message("Click alarma")
                }
                binding.tableView.apply {
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    adapter = actividadAdapter
                }
                showActividades()
            }
            else {
                showEmptyActividades()
            }
        }

    }


    private fun loadMascotas() {
        dashboardViewModel.getAllMascotas()

        dashboardViewModel.mascotasList.observe(viewLifecycleOwner) { mascotas ->
            this.mascotasList = ArrayList(mascotas)
        }
    }



    private fun showLoadingActividades() {
        binding.tableView.visibility = View.GONE
        binding.emptyMessageText.visibility = View.GONE
        binding.progressbarr.visibility = View.VISIBLE
    }

    private fun showEmptyActividades() {
        binding.tableView.visibility = View.GONE
        binding.emptyMessageText.visibility = View.VISIBLE
        binding.progressbarr.visibility = View.GONE
    }

    private fun showActividades() {
        binding.tableView.visibility = View.VISIBLE
        binding.emptyMessageText.visibility = View.GONE
        binding.progressbarr.visibility = View.GONE
    }

    private fun saveActividad(act: ActividadEntity) {
        dashboardViewModel.saveActividad(act)
    }


    private fun showEventForm() {
        if (mascotasList != null && mascotasList.isNotEmpty()) {
            val params = Bundle()
            params.putParcelableArrayList("mascotas", mascotasList)

            val eventDialog = EventDialogFragment().apply {
                arguments = params
            }

            activity?.let { myActivity ->
                eventDialog.show(myActivity.supportFragmentManager, "EventDialog")
            }
        }
        else {
            showAlert("Error", "Debe agregar una mascota primero", "Aceptar")
        }
    }


}