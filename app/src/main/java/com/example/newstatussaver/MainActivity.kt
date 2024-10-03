package com.example.newstatussaver

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.newstatussaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }

        // Use ViewBinding for activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupRecyclerView()
//        fetchQuranData()

//        Set up the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

//    private fun setupRecyclerView() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//    }
//
//    private fun fetchQuranData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = apiService.getQuranData()
//
//                if (response.isSuccessful) {
//                    val quranData = response.body()
//                    quranData?.let {
//                        runOnUiThread {
//                            // Update RecyclerView with the fetched data
//                            val adapter = SurahAdapter(it.data)
//                            binding.recyclerView.adapter = adapter
//                        }
//                    }
//                } else {
//                    Log.e(TAG, "Error fetching Quran data: ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Exception occurred while fetching Quran data: ${e.message}")
//            }
//        }
//    }
}
