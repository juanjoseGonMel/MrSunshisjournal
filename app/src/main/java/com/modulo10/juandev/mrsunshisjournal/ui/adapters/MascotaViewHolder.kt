package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.media.MediaMetadataRetriever
import android.os.Build
import android.provider.Settings.Global.getString
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.PetElementBinding
import java.util.Calendar
import java.util.Date


class MascotaViewHolder(
    private val binding: PetElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(mascotaFile: MascotaEntity){


        // Asignamos datps
        binding.tvMascotaName.text = mascotaFile.name
        if (mascotaFile.genero == "Macho") {
            binding.ivGenero.setImageResource(R.drawable.ic_male)
        } else {
            binding.ivGenero.setImageResource(R.drawable.ic_female)
        }

        // Ejemplo de fecha de nacimiento (por ejemplo, 1 de enero de 1990)
        //val birthDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("01/01/1990") ?: Date()

        // Calcular la edad
        val age = calcularEdad(mascotaFile.cumple)

        // Asignar el texto al TextView con el texto calculado
        //binding.tvEdad.text = getString(R.string.age_label, age)
        binding.tvEdad.text = String.format(binding.root.context.getString(R.string.age_label), age)

        Glide.with(binding.root.context)
            .load(mascotaFile.photo)
            .error(R.drawable.ic_pets)
            .into(binding.petImage)

        binding.tvDescripcion.text = mascotaFile.descripcion

        binding.tvPesoLabel.text = mascotaFile.pesoactual.toString()
        binding.tvRazaLabel.text = mascotaFile.raza
        if (mascotaFile.esteril){
            binding.ivInvisible.visibility = View.VISIBLE
        }else{
            binding.ivInvisible.visibility = View.GONE
        }


    }

    // Función para calcular la edad basada en la fecha de nacimiento
    private fun calculateAge(birthDate: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = birthDate

        val currentYear = calendar.get(Calendar.YEAR)

        val birthYear = calendar.get(Calendar.YEAR)

        return currentYear - birthYear


    }

    private fun calcularEdad(fechaNacimiento: Date): Int {
        val nacimiento = Calendar.getInstance().apply {
            time = fechaNacimiento
        }
        val hoy = Calendar.getInstance()

        var edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR)

        if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) { edad-- }
        return edad
    }

}
