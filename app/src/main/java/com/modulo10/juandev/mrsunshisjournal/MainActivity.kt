package com.modulo10.juandev.mrsunshisjournal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.modulo10.juandev.mrsunshisjournal.data.JournalRepository
import com.modulo10.juandev.mrsunshisjournal.databinding.ActivityMainBinding
import com.modulo10.juandev.mrsunshisjournal.utils.DatabaseFiller
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    private lateinit var databaseFiller: DatabaseFiller
    private lateinit var repository: JournalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /* Borrar

        databaseFiller = DatabaseFiller(this)

        // Llamamos a la función para llenar la base de datos con datos de prueba
        GlobalScope.launch {
            databaseFiller.fillDatabaseWithTestData()
        }*/

        //repository = (application as MrSunshisJournalApp).repository


        // Inicializar DatabaseFiller
        //databaseFiller = DatabaseFiller(repository)

        // Llamar al método para insertar datos de prueba (se ejecuta en un hilo de fondo)
        //lifecycleScope.launch {
          //  databaseFiller.fillDatabaseWithTestData()
        //}

        /* */


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                //R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_journal, R.id.navigation_notifications
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        /*
         // Aquí, asegúrate de actualizar los botones según el fragmento actual
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
        if (fragment is SomeSpecificFragment) {
            // Si el fragmento activo es SomeSpecificFragment, puedes ocultar el botón "Add"
            menu?.findItem(R.id.action_add)?.isVisible = false
        } else {
            // De lo contrario, asegúrate de que el botón "Add" sea visible
            menu?.findItem(R.id.action_add)?.isVisible = true
        }
        return true




         */


        return true
    }
    */

    // Maneja la acción cuando se selecciona el botón en el ActionBar
    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // Acción del botón "Add"
                Toast.makeText(this, "Add button clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

     */


}