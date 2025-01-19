package com.modulo10.juandev.mrsunshisjournal.ui.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.room.ColumnInfo
import com.modulo10.juandev.mrsunshisjournal.data.db.model.ActividadEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.HabitatEntity
import com.modulo10.juandev.mrsunshisjournal.data.db.model.MascotaEntity
import com.modulo10.juandev.mrsunshisjournal.databinding.DialogEventBinding
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewActividadListener
import com.modulo10.juandev.mrsunshisjournal.ui.listeners.NewHabitatListener
import com.modulo10.juandev.mrsunshisjournal.utils.message
import java.lang.String
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import kotlin.Int
import kotlin.apply
import kotlin.let
import java.util.*


class EventDialogFragment : DialogFragment() {

    private var _binding: DialogEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var mascotasList: List<MascotaEntity>

    private lateinit var mascotasAdapter: ArrayAdapter<MascotaEntity>

    private lateinit var newActividadListener: NewActividadListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = DialogEventBinding.inflate(inflater, container, false)

        initDialog()

        return binding.root
    }

    private fun initDialog() {
        arguments?.let { params ->
            if (params.containsKey("mascotas")) {
                mascotasList = params.getParcelableArrayList<MascotaEntity>("mascotas") ?: emptyList()
            }

            if (mascotasList != null && mascotasList.size > 0) {
                context?.let { myContext ->
                    mascotasAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, mascotasList).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                    binding.mascotasSpn.adapter = mascotasAdapter
                }
            }
        }

        binding.eventTimeTxt.setOnClickListener {
            openTimeSelector()
        }

        binding.eventDateTxt.setOnClickListener {
            openDateSelector()
        }


        /*
        binding.saveEventtBtn.setOnClickListener {

            val alarmName = binding.eventName.text.toString().trim()

            val alarmDesc = binding.eventDescEdt.text.toString().trim()

            val mascotaAlarmSelect = binding.mascotasSpn.selectedItem.toString()

            val alarmtime = binding.eventTimeTxt.text.toString().trim()

            var timeInDouble = 0.0
            val alarmTime = alarmtime.split(":")
            if (alarmTime.size == 2) {
                val hour = alarmTime[0].toDoubleOrNull() ?: 0.0
                val minute = alarmTime[1].toDoubleOrNull() ?: 0.0
                timeInDouble = hour + (minute / 60)
                println("Hora en formato Double: $timeInDouble")
            }

            val alarmdate = binding.eventTimeTxt.text.toString().trim()

            // Convertir la fecha a Date
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val alarmDate = try {
                dateFormat.parse(alarmdate)?: Date()
            } catch (e: Exception) {
                Date()
            }

            if (alarmDate != null) {
                println("Fecha convertida a Date: $alarmDate")
            } else {
                println("Fecha no válida")
            }


            val newAlarm = ActividadEntity(
                name = alarmName,
                mascota = mascotaAlarmSelect,
                tipo = alarmDesc,
                secuencia = timeInDouble,
                inicio = alarmDate,
                final = alarmDate
            )


            newActividadListener.onNewActividad(newAlarm)
            dismiss()

        }


         */
        binding.saveEventtBtn.setOnClickListener {
            message("Se guardo la alarma")
            dismiss()
        }


    }

    private fun openTimeSelector() {
        val newCalendar: Calendar = Calendar.getInstance()

        val timeSelector = TimePickerDialog(
            activity, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    val newDate = Calendar.getInstance()
                    val minuteString = if (minute < 10) "0$minute" else String.valueOf(minute)
                    val hourString =
                        if (hourOfDay < 10) "0$hourOfDay" else String.valueOf(hourOfDay)
                    val time = "$hourString:$minuteString:00"
                    binding.eventTimeTxt.text = time
                }
            },
            newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true
        )
        timeSelector.show()
    }

    private fun openDateSelector() {
        val newCalendar = Calendar.getInstance()
        val dateSelector = DatePickerDialog(
            requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    val newDate = Calendar.getInstance()
                    newDate[year, month] = dayOfMonth
                    val dateFormatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                    binding.eventDateTxt.setText(dateFormatter.format(newDate.time))
                }

            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        dateSelector.datePicker.minDate = System.currentTimeMillis() - 1000
        dateSelector.show()
    }
}