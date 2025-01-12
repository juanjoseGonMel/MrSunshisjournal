package com.modulo10.juandev.mrsunshisjournal.utils

import android.app.Activity
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.message(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}

fun Fragment.message(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}


inline fun <reified T : Parcelable> Bundle.retrieveParcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}