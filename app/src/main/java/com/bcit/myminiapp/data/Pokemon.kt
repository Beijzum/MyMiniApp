package com.bcit.myminiapp.data

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("data")
    val pokemonList: List<PokemonDetails>
)

data class PokemonDetails(
    val id: String,
    val name: String,
)