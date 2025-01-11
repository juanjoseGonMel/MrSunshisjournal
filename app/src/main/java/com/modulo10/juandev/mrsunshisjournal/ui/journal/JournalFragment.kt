package com.modulo10.juandev.mrsunshisjournal.ui.journal

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentJournalBinding
import com.modulo10.juandev.mrsunshisjournal.ui.dashboard.DashboardViewModel
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewActividadListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewNotificationListener

class JournalFragment : Fragment() {

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JournalViewModel by viewModels()



    private var notificationsList = arrayListOf<NotificationsEntity>()


    private lateinit var createNotificationListener: NewNotificationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}