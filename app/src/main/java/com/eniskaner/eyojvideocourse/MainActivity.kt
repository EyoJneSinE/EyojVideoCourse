package com.eniskaner.eyojvideocourse

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import com.eniskaner.courselist.navigation.CoursesNavGraph
import com.eniskaner.coursevideo.ui.util.FullscreenHandler
import com.eniskaner.eyojvideocourse.databinding.ActivityMainBinding
import com.eniskaner.navigationcourseapp.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FullscreenHandler{

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigationGraph: @JvmSuppressWildcards Set<NavigationGraph>

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setupWindowInsets()
        setupSystemBars()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupNavGraph()
    }

    private fun setupNavGraph() {
        navController.graph = navController.createGraph(
            startDestination = CoursesNavGraph.ROUTE,
        ) {
            navigationGraph.forEach { navNodes ->
                navNodes.addNav(this)
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

    @RequiresApi(Build.VERSION_CODES.R)
    override fun enterFullscreen() {
        window.insetsController?.hide(WindowInsets.Type.systemBars())
        window.insetsController?.systemBarsBehavior =
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun exitFullscreen() {
        window.insetsController?.show(WindowInsets.Type.systemBars())
    }
}
