package com.modulo10.juandev.mrsunshisjournal.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.Index
import androidx.room.Relation
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import java.util.Date

@Entity(
    tableName = Constants.DATABASE_ACTIVIDAD_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = MascotaEntity::class,
            parentColumns = ["pet_id"],
            childColumns = ["pet_owner_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ActividadEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "act_id")
    var id: Long = 0,
    @ColumnInfo(name = "act_name")
    var name: String,
    @ColumnInfo(name = "act_tipo")
    var tipo: String,
    @ColumnInfo(name = "act_time")
    var time: Date,
    @ColumnInfo(name = "act_photo")
    var photo: String?,
    @ColumnInfo(name = "act_notas")
    var notas: String,
    @ColumnInfo(name = "act_dateinicio")
    var inicio: Date,
    @ColumnInfo(name = "act_datefin")
    var final: Date,
    @ColumnInfo(name = "pet_owner_id")
    val mascotaId: Long  // Referencia al habitat
)
