package com.bsoftware.myapplication.navBarNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bsoftware.myapplication.AccountUserData
import com.bsoftware.myapplication.OurProductShow
import com.bsoftware.myapplication.ShowMainMenu

@Composable
fun NavigationsFunction(navController : NavHostController){
    NavHost(navController = navController, startDestination = NavigationItem.Home.route){
        composable(NavigationItem.Home.route){
            // into MainMenuActivity
            ShowMainMenu()
        }

        composable(NavigationItem.Cando.route){
            // into OutProduct maybe
            OurProductShow()
        }

        composable(NavigationItem.Account.route){
            // into AccountActivity
            AccountUserData()
        }
    }

}