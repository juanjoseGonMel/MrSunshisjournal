package com.modulo10.juandev.mrsunshisjournal.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Index
import androidx.room.Relation
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import java.util.Date

@Entity(
    tableName = Constants.DATABASE_NOTIFICATIONS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = MascotaEntity::class,
            parentColumns = ["pet_id"],
            childColumns = ["pet_owner_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActividadEntity::class,
            parentColumns = ["act_id"],
            childColumns = ["act_owner_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotificationsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notification_id")
    var id: Long = 0,

    @ColumnInfo(name = "notification_time")
    var time: Date,

    @ColumnInfo(name = "notification_photo")
    var photo: String?,

    @ColumnInfo(name = "notification_notas")
    var notas: String,

    @ColumnInfo(name = "notification_comida")
    var comida: Float,

    @ColumnInfo(name = "notification_temp")
    var temperatura: Float,

    @ColumnInfo(name = "notification_dosis")
    var dosis: String,

    @ColumnInfo(name = "notification_aseo")
    var aseo: String,


    @ColumnInfo(name = "pet_owner_id")
    val mascotaId: Long,  // Referencia a mascota

    @ColumnInfo(name = "act_owner_id")
    val actId: Long

)

// Clase de relación entre Notificaciones, Mascotas y Actividades

data class NotificationsConMascotaYActividad(
    @Embedded val notification: NotificationsEntity,

    @Relation(
        parentColumn = "pet_owner_id",
        entityColumn = "pet_id"
    )
    val mascota: MascotaEntity,

    @Relation(
        parentColumn = "act_owner_id",
        entityColumn = "act_id"
    )
    val actividad: ActividadEntity
)
