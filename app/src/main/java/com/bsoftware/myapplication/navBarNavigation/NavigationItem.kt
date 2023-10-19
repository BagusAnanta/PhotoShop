package com.bsoftware.myapplication.navBarNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.bsoftware.myapplication.R

sealed class NavigationItem(var route : String, val icon : ImageVector?, var title : String){
    object Home : NavigationItem(route = "Home", icon = Icons.Rounded.Home,"Home")
    object Cando : NavigationItem(route = "Cando", icon = Icons.Rounded.Add,"Cando")
    object Account : NavigationItem(route = "Account", icon = Icons.Rounded.AccountCircle, "Account")
}

