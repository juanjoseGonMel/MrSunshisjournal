package com.modulo10.juandev.mrsunshisjournal.utils

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    // Convierte java.util.Date a Long (milisegundos)
    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time  // Retorna la fecha en milisegundos
    }

    // Convierte Long (milisegundos) a java.util.Date
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }  // Convierte el Long de milisegundos a Date
    }
}
