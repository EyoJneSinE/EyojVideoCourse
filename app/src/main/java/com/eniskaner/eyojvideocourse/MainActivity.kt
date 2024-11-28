package com.eniskaner.eyojvideocourse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.courselist.navigation.CoursesNavGraph
import com.eniskaner.eyojvideocourse.databinding.ActivityMainBinding
import com.eniskaner.navigationcourseapp.NavigationGraph
import com.eniskaner.profile.navigation.CourseProfileNavGraph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    lateinit var navigationGraph: @JvmSuppressWildcards Set<NavigationGraph>

    private lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = Firebase.auth

        setupWindowInsets()
        setupSystemBars()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupNavGraph()
        setupBottomNavigation()
        setupBottomNavigationVisibility()

        val isDarkMode = preferencesManager.isDarkMode
        toggleDarkMode(isDarkMode)
    }

    private fun setupBottomNavigationVisibility() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            val currentRoute = navController.currentDestination?.route
            binding.bottomNavigation.isVisible = when (currentRoute) {
                CourseAuthNavGraph.START_DESTINATION,
                     CourseAuthNavGraph.LOGIN_DESTINATION,
                         CourseAuthNavGraph.REGISTER_DESTINATION -> false
                else -> true
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_courses -> {
                    navigateToFeature(CoursesNavGraph.ROUTE)
                    true
                }
                R.id.navigation_profile -> {
                    navigateToFeature(CourseProfileNavGraph.ROUTE)
                    true
                }
                else -> false
            }

        }
    }

    private fun navigateToFeature(route: String) {
        navController.graph = navController.createGraph(
            startDestination = route,
        ) {
            navigationGraph.forEach { navNodes ->
                navNodes.addNav(this)
            }
        }
    }

    private fun setupNavGraph() {
        if (auth.currentUser != null) {
            navController.graph = navController.createGraph(
                startDestination = CoursesNavGraph.ROUTE,
            ) {
                navigationGraph.forEach { navNodes ->
                    navNodes.addNav(this)
                }
            }
        } else {
            navController.graph = navController.createGraph(
                startDestination = CourseAuthNavGraph.ROUTE,
            ) {
                navigationGraph.forEach { navNodes ->
                    navNodes.addNav(this)
                }
            }
        }
    }

    private fun setupSystemBars() {
        window.let { window ->
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun toggleDarkMode(enable: Boolean) {
        val mode = if (enable) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
