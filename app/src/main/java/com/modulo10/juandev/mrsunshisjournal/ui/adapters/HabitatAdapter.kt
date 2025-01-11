package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.HabitatElementBinding


class HabitatAdapter(
    private val habitats : MutableList<HabitatEntity>,
    private val onHabitatClick : (Int) -> Unit
) : RecyclerView.Adapter<HabitatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitatViewHolder {
        val binding = HabitatElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitatViewHolder(binding)
    }

    override fun getItemCount(): Int = habitats.size

    override fun onBindViewHolder(holder: HabitatViewHolder, position: Int) {
        holder.bind(habitats[position])
        holder.itemView.setOnClickListener{
            onHabitatClick(position)
        }

    }

}
