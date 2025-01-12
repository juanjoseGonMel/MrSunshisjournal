package com.modulo10.juandev.mrsunshisjournal.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentHomeBinding
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Esperar unos segundos antes de navegar al siguiente fragmento
        /*
        Handler().postDelayed({
            // Navegar al siguiente fragmento
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToNavigationHome()
            )
        }, 2000) // Espera 2 segundos para mostrar el splash

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}