package com.bsoftware.myapplication.navBarNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsoftware.myapplication.AccountUserData
import com.bsoftware.myapplication.OurProductShow
import com.bsoftware.myapplication.ShowMainMenu
import com.bsoftware.myapplication.navBarNavigation.ui.theme.MyApplicationTheme

class BottomNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    
                }
            }
        }
    }
}

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

@Composable
fun BottomNavigationBar(navController : NavController){
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Cando,
        NavigationItem.Account
    )

    var itemSelected by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Home.route) }

    items.forEachIndexed { index, navigationItem ->
        if(navigationItem.route == currentRoute){
            itemSelected = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.title)},
                label = { Text(text = item.title)},
                selected = itemSelected == index,
                onClick = {
                    itemSelected = index
                    currentRoute = item.route
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

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





@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        BottomNavigationShow(navController = navController)
    }
}