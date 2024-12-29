package com.modulo10.juandev.mrsunshisjournal.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.Index
import androidx.room.Relation
import com.modulo10.juandev.mrsunshisjournal.utils.Constants
import java.util.Date

@Entity(tableName = Constants.DATABASE_ACTIVIDAD_TABLE)
data class ActividadEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "act_id")
    var id: Long = 0,

    @ColumnInfo(name = "act_name")
    var name: String,

    @ColumnInfo(name = "act_tipo")
    var tipo: String,

    @ColumnInfo(name = "act_dateinicio")
    var inicio: Date,
    @ColumnInfo(name = "act_datefin")
    var final: Date

)
