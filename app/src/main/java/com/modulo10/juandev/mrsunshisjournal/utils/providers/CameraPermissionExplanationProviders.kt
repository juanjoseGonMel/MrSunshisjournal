package com.modulo10.juandev.mrsunshisjournal.utils.providers

import com.modulo6.musicplayerdiplo.ui.providers.PermissionExplanationProviders


class CameraPermissionExplanationProviders : PermissionExplanationProviders {
    override fun getPermissionText(): String = "Permiso para usar la camara"

    override fun getExplanation(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "El permiso se ha negado permanentemente. Para usar esta aplicacion, habilite el permiso en la configuracion"
        } else {
            "Se requiere el permiso solamente para acceder a la camara"
        }
    }
}
