package com.modulo10.juandev.mrsunshisjournal.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.DATABASE_HABITAT_TABLE)
data class HabitatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habitat_id")
    var id: Long = 0,

    @ColumnInfo(name = "habitat_name")
    var name: String,

    @ColumnInfo(name = "habitat_photo")
    var photo: String?,

    @ColumnInfo(name = "habitat_descripcion")
    var descripcion: String,

    @ColumnInfo(name = "habitat_capacidad")
    var capacidad: Int = 1,

    @ColumnInfo(name = "habitat_tipo", defaultValue = "Casa")
    var tipo: String,

    @ColumnInfo(name = "habitat_tamaño")
    var size: Double,

    @ColumnInfo(name = "habitat_temperatura")
    var temperatura: Double,

    @ColumnInfo(name = "habitat_acceso")
    var abierta: Boolean = false
) : Parcelable {
    override fun toString(): String {
        return name
    }
}

