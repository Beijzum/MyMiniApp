package com.bcit.myminiapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.bcit.myminiapp.data.Endpoints
import com.bcit.myminiapp.ui.state.PokemonState
import kotlinx.coroutines.delay

@Composable
fun Home(navController: NavController) {

    var pokemonState: PokemonState = viewModel(navController.getBackStackEntry("home"))

    val (isLoading, setLoading) = remember { mutableStateOf(true) }

    LaunchedEffect(pokemonState.searchFlow.collectAsState().value) {
        setLoading(true)
        delay(8000)
        setLoading(false)
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField( // emits every time type
            modifier = Modifier.padding(top = 10.dp),
            value = pokemonState.searchFlow.collectAsState().value, onValueChange = {
                pokemonState.searchFlow.value = it
            },
            textStyle = TextStyle(fontSize = 40.sp),
            placeholder = {
                Text(text = "Enter Pokemon name", fontSize = 24.sp)
            }
        )
        Spacer(Modifier.height(16.dp))

        if (pokemonState.pokemon.isEmpty()) {
            if (isLoading) {
                Text(
                    text = "Loading...",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                Text(
                    text = "No Pokémon found",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        } else {
            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(pokemonState.pokemon.size) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("details/${pokemonState.pokemon[it].id}")
                            }
                    ) {
                        Text(
                            pokemonState.pokemon[it].name,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    AsyncImage(
                        model = Endpoints.IMAGE_ENDPOINT.format(
                            pokemonState.pokemon[it].id.replace("-", "/")
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .clickable {
                                navController.navigate("details/${pokemonState.pokemon[it].id}")
                            }
                    )
                }
            }
        }
    }
}