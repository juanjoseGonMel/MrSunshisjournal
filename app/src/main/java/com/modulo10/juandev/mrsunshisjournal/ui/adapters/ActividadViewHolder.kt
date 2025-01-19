package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.AlarmElementBinding


class ActividadViewHolder(
    private val binding: AlarmElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(actividadFile: ActividadEntity) {

        // Asignamos el nombre del hábitat

        Glide.with(binding.root)
            .load(actividadFile.name)
            .error(R.drawable.ic_pets)
            .into(binding.petImage)

        Glide.with(binding.root)
            .load(actividadFile.name)
            .error(R.drawable.ic_scale)
            .into(binding.typeImage)


    }
}