package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myminiapp.ui.state.ArtState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artRepository = (application as MyApp).artRepository

        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    MyTopNav(navController)
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(padding)) {
                    composable("home") {
                        viewModel(navController.getBackStackEntry("home")) {
                            ArtState(artRepository)
                        }
                        Home(navController)
                    }
                    composable("details") {
                        Details(navController)
                    }
                }
            }
        }

    }
}