package com.bcit.myminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcit.myminiapp.data.PokemonDetails
import com.bcit.myminiapp.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class PokemonState(private val pokemonRepository: PokemonRepository)  : ViewModel() {

    var pokemon = mutableStateListOf<PokemonDetails>()

    var searchFlow = MutableStateFlow("")

    init {
        searchFlow.value = ""
        collectSearchInputs()
    }

    private fun collectSearchInputs() {
        viewModelScope.launch {
            searchFlow.debounce(1000L)
                .collect {
                    search(it)
                }
        }
    }

    suspend fun search(str: String) {
        pokemon.also {
            it.clear()
            it.addAll(pokemonRepository.search(str).pokemonList)
        }
        println(pokemon)
    }
}