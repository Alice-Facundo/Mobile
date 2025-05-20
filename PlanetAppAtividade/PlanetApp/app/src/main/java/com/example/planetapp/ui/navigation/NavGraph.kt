package com.example.planetapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.planetapp.models.Planet
import com.example.planetapp.models.planetList
import com.example.planetapp.ui.components.BottomNavigationBar
import com.example.planetapp.ui.screens.DetailsScreen
import com.example.planetapp.ui.screens.FavoritesScreen
import com.example.planetapp.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarScreen.Home.route) {
                HomeScreen(
                    onPlanetSelected = { planet ->
                        navController.navigate("details/${planet.name}")
                    },
                    onSettingsClick = onSettingsClick,
                    onHelpClick = onHelpClick
                )
            }

            composable(BottomBarScreen.Favorites.route) {
                FavoritesScreen(
                    onPlanetSelected = { planet ->
                        navController.navigate("details/${planet.name}")
                    },
                    onFavoriteToggle = { planet ->
                        planet.isFavorite = !planet.isFavorite
                    }
                )
            }

            composable(
                route = "details/{planetName}",
                arguments = listOf(navArgument("planetName") { type = NavType.StringType })
            ) { backStackEntry ->
                val planetName = backStackEntry.arguments?.getString("planetName")
                val selectedPlanet = planetList.first { it.name == planetName }
                DetailsScreen(planet = selectedPlanet)
            }
        }
    }
}
