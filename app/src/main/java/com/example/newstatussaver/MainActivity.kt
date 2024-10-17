package com.example.newstatussaver

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newstatussaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//
//        navController.addOnDestinationChangedListener{ _, destination, _ ->
//            when(destination.id){
//                R.id.splashFragment, R.id.compSurahFragment -> binding.bottomNav.visibility = View.GONE
//                R.id.detailsFragment, R.id.bookmarkFragment -> binding.bottomNav.visibility = View.VISIBLE
//                else -> binding.bottomNav.visibility = View.GONE
//                }
//        }
        binding.bottomNav.setupWithNavController(navController)
    }
}