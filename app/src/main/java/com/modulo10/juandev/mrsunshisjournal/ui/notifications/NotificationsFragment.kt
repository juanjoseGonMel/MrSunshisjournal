package com.modulo10.juandev.mrsunshisjournal.ui.notifications


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.databinding.FragmentNotificationsBinding
import com.modulo10.juandev.mrsunshisjournal.utils.message

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!


    private lateinit var sp : SharedPreferences
    private lateinit var spEditor : SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //instanciamos SharedPreferences
        sp = requireActivity().getPreferences(Context.MODE_PRIVATE)
        spEditor = sp.edit()

        /*
        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        */

        // Configurar el MaterialAutoCompleteTextView (selector de localización)
        /*
        val locations = resources.getStringArray(R.array.localizacion_options)
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, locations)
        binding.spinnerLocalizacion.setAdapter(adapter)
        */

        // Configurar el Spinner (selector de localización)
        val locations = resources.getStringArray(R.array.localizacion_options)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLocalizacion.adapter = adapter



        // Cargar los datos de una fuente de datos externa
        cargarUsuario()


        //Boton de guardar
        binding.btnGuardar.setOnClickListener {
            // Lógica del botón, por ejemplo, guardar los datos
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val email = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            //val location = binding.spinnerLocalizacion.text.toString() //En caso de usar MaterialAutoComplete
            val location = binding.spinnerLocalizacion.selectedItem.toString()

            guardarUsuario(nombre,apellido,email,telefono,location)

            message("Datos enviados: $nombre, $apellido ,$email, $telefono, $location")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Guardar los datos en SharedPreferences
    private fun guardarUsuario(nombre: String, apellido: String, email: String, telefono: String, location: String) {

        spEditor.apply {
            putString("nombre", nombre)
            putString("apellido", apellido)
            putString("email", email)
            putString("telefono", telefono)
            putString("location", location)
            apply()
        }

        /*
        val sharedPref = requireActivity().getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("nombre", nombre)
            putString("apellido", apellido)
            putString("email", email)
            putString("telefono", telefono)
            putString("location", location)
            apply() // Usa apply() para guardar los cambios de forma asíncrona
        }

         */
    }

    // Cargar los datos desde SharedPreferences
    private fun cargarUsuario() {

        val name = sp.getString("nombre", "") ?: ""
        val surname = sp.getString("apellido", "") ?: ""
        val email = sp.getString("email", "") ?: ""
        val phone = sp.getString("telefono", "") ?: ""
        val location = sp.getString("location", "") ?: ""

        val locations = resources.getStringArray(R.array.localizacion_options)

        binding.apply {
            userImage.setImageResource(R.drawable.ic_person)  // Imagen predeterminada
            etNombre.setText(name)
            etApellido.setText(surname)
            etCorreo.setText(email)
            etTelefono.setText(phone)

            // Asignar la localización, buscamos la opción correcta y la asignamos
            val locationIndex = locations.indexOf(location) // Buscar el índice de la localización seleccionada
            if (locationIndex >= 0) {
                //spinnerLocalizacion.setText(locations[locationIndex])  // Asignamos el texto materialauto
                spinnerLocalizacion.setSelection(locationIndex)  // Establecer el índice en el Spinner
            }
        }

        //message("Datos cargados: $name, $surname,$email , $phone, $location")

        /*
        val sharedPref = requireActivity().getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        val name = sharedPref.getString("name", "") ?: ""
        val surname = sharedPref.getString("surname", "") ?: ""
        val email = sharedPref.getString("email", "") ?: ""
        val phone = sharedPref.getString("phone", "") ?: ""
        val location = sharedPref.getString("location", "") ?: ""

        binding.apply {
            userImage.setImageResource(R.drawable.ic_notification_overlay)  // Imagen predeterminada
            etNombre.setText(name)  // Cargar nombre
            etApellido.setText(surname)  // Cargar apellido
            etCorreo.setText(email)  // Cargar correo
            etTelefono.setText(phone)  // Cargar teléfono
            spinnerLocalizacion.setText(location)  // Cargar localización
        }
         */
    }

}