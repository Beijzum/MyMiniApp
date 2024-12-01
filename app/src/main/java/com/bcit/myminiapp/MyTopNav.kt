package com.bcit.myminiapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopNav(navController: NavController) {

    TopAppBar(
        modifier = Modifier.padding(0.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFADD8E6)),
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(69.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Pokemon Card Search",
                    fontSize = 20.sp,
                )
                IconButton(
                    onClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.navigateUp()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}