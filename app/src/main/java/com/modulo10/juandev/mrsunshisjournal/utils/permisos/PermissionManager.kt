package com.modulo10.juandev.mrsunshisjournal.utils.permisos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.modulo6.musicplayerdiplo.ui.providers.PermissionExplanationProviders
import com.modulo6.musicplayerdiplo.ui.providers.ReadAudioPermissionExplanationProviders
import com.modulo6.musicplayerdiplo.ui.providers.ReadPermissionExplanationProviders
import com.modulo6.musicplayerdiplo.ui.providers.WritePermissionExplanationProviders

class PermissionManager{}

/*
class PermissionManager(
    private val fragment: Fragment,
    private val permissionViewModel: PermissionViewModel,
    private val permissionsLauncher: ActivityResultLauncher<Array<String>>,
    private val permissionsResultCallback: (permissionsMap: Map<String, Boolean>) -> Unit
) {

    fun checkAndRequestPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        // Añadimos permisos según el caso
        if (!isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !isPermissionGranted(Manifest.permission.READ_MEDIA_AUDIO)
        ) {
            permissionsToRequest.add(Manifest.permission.READ_MEDIA_AUDIO)
        }

        if (permissionsToRequest.isNotEmpty()) {
            permissionsLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            permissionsResultCallback(emptyMap())
        }

        // Actualizamos la lista de permisos a solicitar en el ViewModel
        permissionsToRequest.forEach { permission ->
            permissionViewModel.addPermissionToQueue(permission)
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    fun handlePermissionsResult(permissionsMap: Map<String, Boolean>) {
        permissionsMap.forEach { (permission, isGranted) ->
            if (isGranted) {
                permissionViewModel.onPermissionGranted(permission)
            } else {
                permissionViewModel.onPermissionDenied(permission)
            }
        }

        permissionsResultCallback(permissionsMap)
    }

    fun showPermissionExplanationDialog(
        permission: String,
        onDismiss: () -> Unit,
        onOkClick: () -> Unit,
        onGoToAppSettingsClick: () -> Unit
    ) {
        val permissionExplanationProvider = getPermissionExplanationProvider(permission)

        AlertDialog.Builder(fragment.requireContext())
            .setTitle(permissionExplanationProvider.getPermissionText())
            .setMessage(permissionExplanationProvider.getExplanation(false))
            .setPositiveButton("Entendido") { _, _ -> onOkClick() }
            .setOnDismissListener { onDismiss() }
            .setNegativeButton("Configuración") { _, _ -> onGoToAppSettingsClick() }
            .show()
    }

    private fun showPermissionExplanationDialog(
        permissionExplanationProviders: PermissionExplanationProviders,
        isPermanentlyDeclined: Boolean,
        onDismiss : () -> Unit,
        onOkClick : () -> Unit,
        onGoToAppSettingsClick : () -> Unit
    ){
        AlertDialog.Builder(requireContext())
            .setTitle(permissionExplanationProviders.getPermissionText())
            .setMessage(permissionExplanationProviders.getExplanation(isPermanentlyDeclined))
            .setPositiveButton(if(isPermanentlyDeclined)"Configuracion" else "Entendido"){ _, _ ->
                if(isPermanentlyDeclined) onGoToAppSettingsClick()
                else onOkClick()
            }
            .setOnDismissListener{_ ->
                onDismiss()
            }
            .show()
    }

    private fun getPermissionExplanationProvider(permission: String): PermissionExplanationProviders {
        return when (permission) {
            Manifest.permission.READ_MEDIA_AUDIO -> ReadAudioPermissionExplanationProviders()
            Manifest.permission.READ_EXTERNAL_STORAGE -> ReadPermissionExplanationProviders()
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> WritePermissionExplanationProviders()
            else -> throw IllegalArgumentException("Unknown permission")
        }
    }


}


class PermissionManager(
    private val context: Context,
    private val permissionExplanationProviders: Map<String, PermissionExplanationProviders>
) {

    fun checkPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            // Permiso ya concedido
            return
        }

        val isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)
        val provider = permissionExplanationProviders[permission]

        provider?.let {
            showPermissionDialog(it, isPermanentlyDeclined, permission)
        }
    }

    private fun showPermissionDialog(provider: PermissionExplanationProviders, isPermanentlyDeclined: Boolean, permission: String) {
        val message = provider.getExplanation(isPermanentlyDeclined)

        val builder = AlertDialog.Builder(context)
            .setMessage(message)
            .setCancelable(false)

        if (isPermanentlyDeclined) {
            builder.setPositiveButton("Go to settings") { _, _ ->
                // Redirigir a la configuración
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }
        } else {
            builder.setPositiveButton("Allow") { _, _ ->
                ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 100)
            }
            builder.setNegativeButton("Deny") { _, _ ->
                // No hacer nada
            }
        }

        builder.show()
    }

    // Método para manejar el resultado de la solicitud de permiso
    fun handlePermissionResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 100) {
            permissions.forEachIndexed { index, permission ->
                if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso fue concedido
                    return
                } else {
                    // El permiso fue denegado
                    checkPermission(permission)
                }
            }
        }
    }
}


/*
class PermissionManager(
    private val fragment: Fragment,
    private val permissionViewModel: PermissionViewModel,
    private val permissionsLauncher: ActivityResultContracts.RequestMultiplePermissions,
    private val onPermissionsResult: (Map<String, Boolean>) -> Unit
) {

    private var permissionsToRequest = mutableListOf<String>()
    private var permissionsExplanationMap = mutableMapOf<String, PermissionExplanationProviders>()

    // Esta función solo se encargará de setear los permisos, las explicaciones estarán configuradas internamente
    fun setPermissionsToCheck(permissions: List<String>) {
        permissionsToRequest.clear()
        permissionsToRequest.addAll(permissions)

        // Cargar las explicaciones de los permisos internamente
        loadPermissionExplanations()
    }

    // Cargar las explicaciones de los permisos de forma interna
    private fun loadPermissionExplanations() {
        permissionsExplanationMap[Manifest.permission.CAMERA] = CameraPermissionExplanationProvider()
        permissionsExplanationMap[Manifest.permission.INTERNET] = InternetPermissionExplanationProvider()
        permissionsExplanationMap[Manifest.permission.READ_EXTERNAL_STORAGE] = StoragePermissionExplanationProvider()
    }

    fun checkAndRequestPermissions() {
        val permissionsMap = mutableMapOf<String, Boolean>()

        // Verificar permisos
        permissionsToRequest.forEach { permission ->
            permissionsMap[permission] = ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        // Verificar si algunos permisos no fueron concedidos
        val permissionsDenied = permissionsMap.filter { !it.value }

        if (permissionsDenied.isEmpty()) {
            // Si todos los permisos fueron concedidos, ejecutar acción
            onPermissionsResult(permissionsMap)
        } else {
            // Si hay permisos denegados, mostrar el diálogo correspondiente
            showPermissionExplanationDialog(permissionsDenied)
        }
    }

    private fun showPermissionExplanationDialog(permissionsDenied: Map<String, Boolean>) {
        // Mostrar un diálogo de explicación para cada permiso denegado
        permissionsDenied.forEach { (permission, _) ->
            val permissionExplanationProvider = permissionsExplanationMap[permission]
            permissionExplanationProvider?.let {
                showDialog(permission, it)
            }
        }
    }

    private fun showDialog(permission: String, permissionExplanationProvider: PermissionExplanationProviders) {
        val isPermanentlyDeclined = !fragment.shouldShowRequestPermissionRationale(permission)
        AlertDialog.Builder(fragment.requireContext())
            .setTitle(permissionExplanationProvider.getPermissionText())
            .setMessage(permissionExplanationProvider.getExplanation(isPermanentlyDeclined))
            .setPositiveButton(if (isPermanentlyDeclined) "Configuración" else "Entendido") { _, _ ->
                if (isPermanentlyDeclined) {
                    goToAppSettings()
                } else {
                    permissionsLauncher.launch(permissionsToRequest.toTypedArray())
                }
            }
            .setOnDismissListener {
                // Actualizar el PermissionViewModel con el estado de los permisos
                permissionsDenied.forEach { (permission, isGranted) ->
                    permissionViewModel.onPermissionResult(permission, isGranted)
                }
            }
            .show()
    }

    private fun goToAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", fragment.requireContext().packageName, null)
        )
        fragment.startActivity(intent)
    }

}

 */