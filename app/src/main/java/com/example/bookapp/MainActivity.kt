package com.example.bookapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private val loginViewModel: LoginViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Navigation configuration - Destinations listed here will have the hamburger icon
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardAdminFragment, 
                R.id.dashboardBibliotecarioFragment,
                R.id.catalogoLibrosFragment,
                R.id.listaUsuariosFragment,
                R.id.reporteMensualFragment,
                R.id.configuracionFragment,
                R.id.loginFragment
            ), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        
        // Initial setup for the NavigationView (Drawer)
        binding.navView.setupWithNavController(navController)

        // Handle drawer navigation item selection manually for logout and consistency
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.loginFragment -> {
                    loginViewModel.logout()
                    navController.navigate(R.id.loginFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> {
                    val handled = androidx.navigation.ui.NavigationUI.onNavDestinationSelected(menuItem, navController)
                    if (handled) binding.drawerLayout.closeDrawer(GravityCompat.START)
                    handled
                }
            }
        }

        // Logic to switch menus based on role
        loginViewModel.usuarioLogueado.observe(this) { usuario ->
            if (usuario != null) {
                binding.bottomNavigation.menu.clear()
                if (usuario.rol == "ADMIN") {
                    binding.bottomNavigation.inflateMenu(R.menu.admin_bottom_nav_menu)
                    binding.drawerLayout.setDrawerLockMode(androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED)
                } else {
                    binding.bottomNavigation.inflateMenu(R.menu.bottom_nav_menu)
                    binding.drawerLayout.setDrawerLockMode(androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                
                // Re-setup with NavController to bind the newly inflated menu items
                binding.bottomNavigation.setupWithNavController(navController)
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.splashFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE
                    binding.drawerLayout.setDrawerLockMode(androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
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

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        // Handle the home button (hamburger/arrow) correctly
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.getDrawerLockMode(GravityCompat.START) == androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED) {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
