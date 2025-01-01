package com.modulo6.musicplayerdiplo.ui.providers

interface PermissionExplanationProviders {
    fun getPermissionText() : String
    fun getExplanation(isPermanentlyDeclined : Boolean) : String
}