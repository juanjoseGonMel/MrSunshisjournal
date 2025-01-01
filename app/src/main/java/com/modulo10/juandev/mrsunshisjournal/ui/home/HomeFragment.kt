package com.modulo10.juandev.mrsunshisjournal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}