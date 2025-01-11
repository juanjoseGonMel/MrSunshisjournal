package com.modulo10.juandev.mrsunshisjournal.utils


import android.content.Context
import androidx.room.Room
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity
import java.util.Date

class DatabaseFiller(private val repository: JournalRepository) {

    // Función para llenar la base de datos con datos de prueba
    suspend fun fillDatabaseWithTestData() {
        // Insertar Actividades
        val actividad1 = ActividadEntity(
            name = "Ejercicio",
            tipo = "Físico",
            inicio = Date(1630128000000),  // Fecha específica en milisegundos
            final =  Date(1630214400000),
            secuencia = 12.0
        )
        val actividad2 = ActividadEntity(
            name = "Alimentación",
            tipo = "Nutrición",
            inicio = Date(1630128000000),  // Fecha específica en milisegundos
            final =  Date(1630314400000),
            secuencia = 12.0
        )
        repository.insertActividad(actividad1)
        repository.insertActividad(actividad2)

        // Insertar Hábitats
        val habitat1 = HabitatEntity(
            name = "Casa Pequeña",
            photo = null,
            descripcion = "Hábitat para mascotas pequeñas",
            capacidad = 2,
            tipo = "Casa",
            size = 15.0,
            temperatura = 22.5,
            abierta = true
        )
        val habitat2 = HabitatEntity(
            name = "Casa Grande",
            photo = null,
            descripcion = "Hábitat para mascotas grandes",
            capacidad = 5,
            tipo = "Casa",
            size = 30.0,
            temperatura = 21.0,
            abierta = false
        )
        repository.insertHabitat(habitat1)
        repository.insertHabitat(habitat2)

        // Insertar Mascotas
        val mascota1 = MascotaEntity(
            name = "Max",
            genero = "Macho",
            cumple = Date(1630123000000),
            photo = null,
            descripcion = "Perro activo",
            raza = "Bulldog",
            esteril = true,
            pesoactual = 20.0,
            habitatId = habitat1.id
        )
        val mascota2 = MascotaEntity(
            name = "Bella",
            genero = "Hembra",
            cumple = Date(1630122000000),
            photo = null,
            descripcion = "Perra tranquila",
            raza = "Labrador",
            esteril = false,
            pesoactual = 30.0,
            habitatId = habitat2.id
        )
        repository.insertMascota(mascota1)
        repository.insertMascota(mascota2)

        // Insertar Notificaciones
        val notificacion1 = NotificationsEntity(
            time = Date(1630129000000),
            photo = null,
            notas = "Hora de la comida",
            comida = 100.0,
            temperatura = 22.0,
            dosis = "2 pastillas",
            aseo = "No necesario",
            mascotaId = mascota1.id,
            actId = actividad2.id
        )
        val notificacion2 = NotificationsEntity(
            time = Date(1630133000000),
            photo = null,
            notas = "Hora de ejercitar a Bella",
            comida = 0.0,
            temperatura = 20.5,
            dosis = "Ninguna",
            aseo = "Baño",
            mascotaId = mascota2.id,
            actId = actividad1.id
        )
        repository.insertNotificacion(notificacion1)
        repository.insertNotificacion(notificacion2)
    }
}

