package com.modulo10.juandev.mrsunshisjournal.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Relation
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import java.util.Date


@Entity(
    tableName = Constants.DATABASE_MASCOTA_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = HabitatEntity::class,
            parentColumns = ["habitat_id"],
            childColumns = ["habitat_owner_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")
    var id: Long = 0,
    @ColumnInfo(name = "pet_name")
    var name: String,
    @ColumnInfo(name = "pet_genero")
    var genero: Boolean,
    @ColumnInfo(name = "pet_cumpleaños")
    var cumple: Date,
    @ColumnInfo(name = "pet_descripcion")
    var descripcion: String,
    @ColumnInfo(name = "pet_raza")
    var raza: String,
    @ColumnInfo(name = "pet_cientifico")
    var razacien: String,
    @ColumnInfo(name = "pet_esteril")
    var esteril: Boolean,
    @ColumnInfo(name = "pet_nacimiento")
    var nacimiento: Boolean,
    @ColumnInfo(name = "pet_peso")
    var pesoactual: Float,
    @ColumnInfo(name = "pet_comida")
    var comida: String,
    @ColumnInfo(name = "habitat_owner_id")
    val habitatId: Long  // Referencia al habitat
)