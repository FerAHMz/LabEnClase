package com.uvg.labenclase.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Explore : NavigationItem("explore", Icons.Filled.Home, "Explorar")
    object Artists : NavigationItem("artists", Icons.Filled.Person, "Artistas")
}
