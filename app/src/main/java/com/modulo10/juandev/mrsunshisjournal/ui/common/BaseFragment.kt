package com.modulo10.juandev.mrsunshisjournal.ui.common

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog

open class BaseFragment : Fragment() {

    open fun showAlert(title: String, message: String, okText: String, onPositive: DialogCallback? = null, isCancelable: Boolean = false) {
        context?.let { myContext ->
            MaterialDialog(myContext).show {
                title(text = title)
                message(text = message)
                positiveButton(text = okText, click = onPositive)
                cancelable(isCancelable)
            }
        }
    }
}