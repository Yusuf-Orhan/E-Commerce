package com.example.e_commerce

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.e_commerce.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpWithNavigationBar()
    }

    private fun setUpWithNavigationBar() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavMain, navHost.findNavController())

        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment || destination.id == R.id.paymentFragment || destination.id == R.id.paymentSuccesFragment) {
                binding.bottomNavMain.visibility = View.GONE
            } else {
                binding.bottomNavMain.visibility = View.VISIBLE
            }
        }
       this.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               if (findNavController(R.id.fragmentContainerView).previousBackStackEntry != null) {
                   findNavController(R.id.fragmentContainerView).popBackStack()
                } else {
                    this@MainActivity.finish()
                }
            }

        })

    }
}
