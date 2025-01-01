package com.modulo10.juandev.mrsunshisjournal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.HabitatElementBinding
import com.modulo10.juandev.mrsunshisjournal.databinding.PetElementBinding

class MascotaAdapter(
    private val mascotas : MutableList<MascotaEntity>,
    private val onMascotaClick : (Int) -> Unit
) : RecyclerView.Adapter<MascotaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val binding = PetElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MascotaViewHolder(binding)
    }

    override fun getItemCount(): Int = mascotas.size

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        holder.bind(mascotas[position])
        holder.itemView.setOnClickListener{
            onMascotaClick(position)
        }
    }

}
