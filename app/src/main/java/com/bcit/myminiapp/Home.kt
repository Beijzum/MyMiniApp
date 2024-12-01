package com.bcit.myminiapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.bcit.myminiapp.data.Endpoints
import com.bcit.myminiapp.ui.state.ArtState

@Composable
fun Home(navController: NavController) {

    var artState: ArtState = viewModel(navController.getBackStackEntry("home"))

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField( // emits every time type
            value = artState.searchFlow.collectAsState().value, onValueChange = {
                artState.searchFlow.value = it
            },
            textStyle = TextStyle(fontSize = 30.sp)
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(artState.artwork.size) {
                Text(artState.artwork[it].name, fontSize = 30.sp)
                Spacer(Modifier.height(30.dp))
                AsyncImage(
                    model = Endpoints.IMAGE_ENDPOINT.format(artState.artwork[it].id.replace("-", "/")),
                    contentDescription = null
                )
            }
        }
    }
}