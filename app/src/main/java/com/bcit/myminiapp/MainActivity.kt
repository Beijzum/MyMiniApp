package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bcit.myminiapp.data.Endpoints
import com.bcit.myminiapp.ui.state.ArtState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artRepository = (application as MyApp).artRepository

        setContent {

            val artState = ArtState(artRepository)

            LaunchedEffect(artState) {
                artState.getArtwork()
            }

            LazyColumn {
                items(artState.artwork.size) {
                    Text(artState.artwork[it].title, fontSize = 30.sp)
                    AsyncImage(
                        model = Endpoints.IMAGE_ENDPOINT.format(artState.artwork[it].image),
                        contentDescription = null
                    )
                }
            }

        }
    }
}