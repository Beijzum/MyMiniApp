package com.bcit.myminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import com.bcit.myminiapp.data.ArtPiece
import com.bcit.myminiapp.data.ArtRepository

class ArtState(private val artRepository: ArtRepository) {

    var artwork = mutableStateListOf<ArtPiece>()

    suspend fun getArtwork() {
        artwork.also { // scope function
            it.clear()
            it.addAll(artRepository.getArtwork().pieces)
        }
    }
}