package com.bcit.myminiapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.bcit.myminiapp.data.Endpoints
import com.bcit.myminiapp.ui.state.PokemonState

@Composable
fun Details(navController: NavController, pokemonId: String?) {
    var pokemonState: PokemonState = viewModel(navController.getBackStackEntry("home"))
    val pokemon = pokemonState.pokemon.find { it.id == pokemonId }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pokemon != null) {
            Text(text = "Name: ${pokemon.name}", fontSize = 24.sp)
            Text(text = "ID: ${pokemon.id}", fontSize = 20.sp)
            AsyncImage(
                model = Endpoints.HIRES_IMAGE_ENDPOINT.format(pokemon.id.replace("-", "/")
                ),
                contentDescription = "Image of ${pokemon.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        } else {
            Text(text = "Pokémon not found", fontSize = 20.sp)
        }
    }
}