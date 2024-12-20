package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myminiapp.ui.state.PokemonState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonRepository = (application as MyApp).pokemonRepository

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
                    modifier = Modifier
                        .padding(padding)
                        .background(Color(0xFFF5F5DC))
                ) {
                    composable("home") {
                        viewModel(navController.getBackStackEntry("home")) {
                            PokemonState(pokemonRepository)
                        }
                        Home(navController)
                    }
                    composable("details/{pokemonId}") {
                        Details(navController, it.arguments?.getString("pokemonId"))
                    }
                }
            }
        }
    }
}