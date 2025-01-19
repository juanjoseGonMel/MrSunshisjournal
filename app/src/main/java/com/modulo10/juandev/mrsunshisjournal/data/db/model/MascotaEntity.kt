package com.modulo10.juandev.mrsunshisjournal.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Relation
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import kotlinx.parcelize.Parcelize
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
@Parcelize
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")
    var id: Long = 0,

    @ColumnInfo(name = "pet_name")
    var name: String,

    @ColumnInfo(name = "pet_genero")
    var genero: String,

    @ColumnInfo(name = "pet_birthday")
    var cumple: Date,

    @ColumnInfo(name = "pet_photo")
    var photo: String?,

    @ColumnInfo(name = "pet_descripcion")
    var descripcion: String,

    @ColumnInfo(name = "pet_raza")
    var raza: String,

    @ColumnInfo(name = "pet_esteril")
    var esteril: Boolean,

    @ColumnInfo(name = "pet_peso")
    var pesoactual: Double,

    @ColumnInfo(name = "habitat_owner_id")
    val habitatId: Long
) : Parcelable {
    override fun toString(): String {
        return name
    }
}


// Clase de relación entre Mascotas, Notificaciones y Actividades

data class MascotaConNotificacionesYHabitat(
    @Embedded val mascota: MascotaEntity,

    @Relation(
        parentColumn = "habitat_owner_id",
        entityColumn = "habitat_id"
    )
    val habitat: HabitatEntity,  // Relación con HabitatEntity

    @Relation(
        parentColumn = "pet_id",
        entityColumn = "pet_owner_id"
    )
    val notificaciones: List<NotificationsEntity>  // Relación con NotificationsEntity
)