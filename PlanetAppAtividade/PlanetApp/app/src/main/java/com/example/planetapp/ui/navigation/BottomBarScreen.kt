package com.example.planetapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

sealed class BottomBarScreen(
    val route: String,
    val icon: @Composable () -> Unit,
    val label: String
) {
    object Home : BottomBarScreen(
        route = "home",
        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
        label = "Home"
    )

    object Favorites : BottomBarScreen(
        route = "favorites",
        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
        label = "Favoritos"
    )
}
