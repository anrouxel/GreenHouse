package fr.anrouxel.greenhouse

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.topAppBar))
    }

    override fun onStart() {
        super.onStart()

        val bottom_nav: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottom_nav.setupWithNavController(navController)

        if (!onBoardingFinished()) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_welcomeScreenFragment)
        }
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = this.getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("onBoarding", false)
    }
}