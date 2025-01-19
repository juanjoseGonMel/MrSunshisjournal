package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.AlarmElementBinding


class ActividadAdapter(
    private val actividades : MutableList<ActividadEntity>,
    private val onActividadClick : (Int) -> Unit
) : RecyclerView.Adapter<ActividadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadViewHolder {
        val binding = AlarmElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActividadViewHolder(binding)
    }

    override fun getItemCount(): Int = actividades.size

    override fun onBindViewHolder(holder: ActividadViewHolder, position: Int) {
        holder.bind(actividades[position])
        holder.itemView.setOnClickListener{
            onActividadClick(position)
        }

    }

}