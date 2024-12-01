package com.bcit.myminiapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.ui.state.PokemonState

@Composable
fun Details(navController: NavController) {
    var pokemonState: PokemonState = viewModel(navController.getBackStackEntry("home"))

}