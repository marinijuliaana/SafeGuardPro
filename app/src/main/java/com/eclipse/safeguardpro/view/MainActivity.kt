package com.eclipse.safeguardpro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // criar a toolbar
    private lateinit var AppBarConfiguration: AppBarConfiguration

    //criar a navegação
    private lateinit var navController: NavController

    //criar o binding
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configura o binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configura a navegação e a toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        AppBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, AppBarConfiguration)
    }
}