package com.modulo6.musicplayerdiplo.ui.providers

class ReadPermissionExplanationProviders : PermissionExplanationProviders {
    override fun getPermissionText(): String = "Permiso de lectura del almacenamiento externo"

    override fun getExplanation(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined)
            "El permiso se ha negado permanentemente. Para usar esta aplicacion, habilite el permiso en la configuracion"
        else
            "Se requiere el permiso de lectura solamente para acceder a los archivos de audio del dispositivo"
    }
}