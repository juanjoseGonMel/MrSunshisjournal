package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.media.MediaMetadataRetriever
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.modulo10.juandev.mrsunshisjournal.R
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.HabitatElementBinding


class HabitatViewHolder(
    private val binding: HabitatElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(habitatFile: HabitatEntity) {

        // Asignamos el nombre del hábitat
        binding.tvHabitatName.text = habitatFile.name

        Glide.with(binding.root)
            .load(habitatFile.photo)
            .error(R.drawable.habitat_casa)
            .into(binding.ivHabitatImage)
    }
}