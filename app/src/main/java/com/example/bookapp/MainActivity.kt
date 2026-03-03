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

        // Navigation configuration - IMPORTANT: We manually specify END (Right)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardAdminFragment, 
                R.id.dashboardBibliotecarioFragment,
                R.id.loginFragment
            ), binding.drawerLayout
        )

        // Setup with Navigation Components
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // Handle navigation item selection manually to ensure it works with END drawer
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.loginFragment) {
                loginViewModel.logout()
                navController.navigate(R.id.loginFragment)
                binding.drawerLayout.closeDrawer(GravityCompat.END)
                true
            } else {
                val handled = androidx.navigation.ui.NavigationUI.onNavDestinationSelected(menuItem, navController)
                if (handled) binding.drawerLayout.closeDrawer(GravityCompat.END)
                handled
            }
        }

        // Handle Bottom Navigation manually to ensure role-based sync
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            androidx.navigation.ui.NavigationUI.onNavDestinationSelected(item, navController)
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
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Correct implementation for RIGHT drawer navigation
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    
    // Manual toggle for the drawer on the toolbar button click
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Check if drawer is needed and if it should open from the END
            val topDestinations = setOf(R.id.dashboardAdminFragment, R.id.dashboardBibliotecarioFragment)
            if (topDestinations.contains(navController.currentDestination?.id) && 
                binding.drawerLayout.getDrawerLockMode(GravityCompat.END) != androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                binding.drawerLayout.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
