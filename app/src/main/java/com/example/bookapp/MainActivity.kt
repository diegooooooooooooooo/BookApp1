package com.example.bookapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory((application as BookApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Navigation configuration - Destinations listed here will NOT have the hamburger icon
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardAdminFragment, 
                R.id.dashboardBibliotecarioFragment,
                R.id.dashboardLectorFragment,
                R.id.catalogoLibrosFragment,
                R.id.listaUsuariosFragment,
                R.id.reporteMensualFragment,
                R.id.configuracionFragment,
                R.id.loginFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Logic to switch menus based on role
        loginViewModel.usuarioLogueado.observe(this) { usuario ->
            if (usuario != null) {
                binding.bottomNavigation.menu.clear()
                when (usuario.rol) {
                    "ADMIN" -> binding.bottomNavigation.inflateMenu(R.menu.admin_bottom_nav_menu)
                    "LECTOR" -> binding.bottomNavigation.inflateMenu(R.menu.lector_bottom_nav_menu)
                    else -> binding.bottomNavigation.inflateMenu(R.menu.bottom_nav_menu)
                }
                
                // Re-setup with NavController to bind the newly inflated menu items
                binding.bottomNavigation.setupWithNavController(navController)
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.splashFragment, R.id.signUpFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    // Bottom navigation visibility is handled by the user observer
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
