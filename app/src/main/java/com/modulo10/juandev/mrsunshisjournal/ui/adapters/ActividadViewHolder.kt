package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.AlarmElementBinding
import java.util.Calendar
import java.util.Date


class ActividadViewHolder(
    private val binding: AlarmElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(actividadFile: ActividadEntity) {

        // Asignamos el nombre del hábitat
        binding.tvAlarmType.text = "alarma"
        binding.tvpetname.text = actividadFile.mascota
        binding.tvHoratio.text = actividadFile.secuencia.toString()
        binding.tvActName.text = actividadFile.name


        /*
        Glide.with(binding.root)
            .load(actividadFile.name)
            .error(R.drawable.ic_pets)
            .into(binding.petImage)

        Glide.with(binding.root)
            .load(actividadFile.name)
            .error(R.drawable.ic_scale)
            .into(binding.typeImage)

         */

    }

}