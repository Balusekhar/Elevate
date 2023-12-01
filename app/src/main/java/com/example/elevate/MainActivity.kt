package com.example.elevate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.elevate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.loginFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Set the default destination to the LoginFragment
        navController.navigate(R.id.loginFragment)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.loginFragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}