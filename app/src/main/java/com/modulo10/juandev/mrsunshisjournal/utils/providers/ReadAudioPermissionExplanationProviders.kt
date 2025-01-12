package com.modulo6.musicplayerdiplo.ui.providers

class ReadAudioPermissionExplanationProviders : PermissionExplanationProviders {
    override fun getPermissionText(): String = "Permiso para leer los archivos de audio"

    override fun getExplanation(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined)
            "El permiso se ha negado permanentemente. Para usar esta aplicacion, habilite el permiso en la configuracion"
        else
            "Se requiere el permiso solamente para acceder a los archivos de audio del dispositivo"
    }
}