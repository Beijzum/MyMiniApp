package com.bcit.myminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcit.myminiapp.data.ArtPiece
import com.bcit.myminiapp.data.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.collections.addAll
import kotlin.text.clear

class ArtState(private val artRepository: ArtRepository)  : ViewModel() {

    var artwork = mutableStateListOf<ArtPiece>()

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
        artwork.also {
            it.clear()
            it.addAll(artRepository.search(str).pieces)
        }
        println(artwork)
    }
}