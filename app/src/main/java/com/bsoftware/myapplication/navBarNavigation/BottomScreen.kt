package com.bsoftware.myapplication.navBarNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationShow(navController : NavHostController){
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = navController)
            }
        },
    ) {
        Box(modifier = Modifier
            .padding(PaddingValues(0.dp, 0.dp, 0.dp,it.calculateBottomPadding()))
        ) {
            // navigation function in here
            NavigationsFunction(navController = navController)
        }
    }
}

