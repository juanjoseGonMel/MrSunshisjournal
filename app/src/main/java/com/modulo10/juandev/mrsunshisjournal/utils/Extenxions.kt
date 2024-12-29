package com.modulo10.juandev.mrsunshisjournal.utils

import android.app.Activity
import android.widget.Toast

fun Activity.message(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}