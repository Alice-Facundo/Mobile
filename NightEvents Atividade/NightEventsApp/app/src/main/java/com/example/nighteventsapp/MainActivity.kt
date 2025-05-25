package com.example.nighteventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge // This import might be unused if not explicitly enabling edge-to-edge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue // Import for DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api // Keep this if IDE complains about experimental API usage
import androidx.compose.material3.ModalNavigationDrawer // Import for ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text // This import might be unused if Text is not directly used in MainActivity
import androidx.compose.material3.rememberDrawerState // Import for rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf // Import for mutableStateOf
import androidx.compose.runtime.remember // Import for remember
import androidx.compose.runtime.rememberCoroutineScope // Import for rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext // Import for LocalContext
import androidx.compose.ui.tooling.preview.Preview // This import might be unused if Preview is not directly used
import androidx.navigation.compose.NavHost // Import for NavHost
import androidx.navigation.compose.composable // Import for composable
import androidx.navigation.compose.rememberNavController // Import for rememberNavController
import com.example.nighteventsapp.ui.components.BottomNavigationBar // Import for BottomNavigationBar
import com.example.nighteventsapp.ui.components.DrawerContent // Import for DrawerContent
import com.example.nighteventsapp.ui.components.TopBar // Import for TopBar
import com.example.nighteventsapp.ui.screens.EventDetailsScreen // Import for EventDetailsScreen
import com.example.nighteventsapp.ui.screens.FavoritesScreen // Import for FavoritesScreen
import com.example.nighteventsapp.ui.screens.HomeScreen // Import for HomeScreen
import com.example.nighteventsapp.ui.screens.SubscribedEventsScreen // Import for SubscribedEventsScreen
import com.example.nighteventsapp.ui.theme.NightEventsAppTheme
import kotlinx.coroutines.launch // Import for launch

@OptIn(ExperimentalMaterial3Api::class) // This annotation is usually placed above the class/function that uses experimental APIs
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // Uncomment this if you want to enable edge-to-edge display

        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val isDarkTheme = remember { mutableStateOf(false) }

            NightEventsAppTheme(darkTheme = isDarkTheme.value) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true, // You might want to control this based on UI state
                    drawerContent = {
                        // Pass a lambda for onSendNotification.
                        // You'll need to define what happens here, e.g., send a test notification
                        // or integrate with your notification logic.
                        // For now, let's just close the drawer.
                        DrawerContent(navController) {
                            scope.launch { drawerState.close() } // Example: Close drawer on notification action
                            // You can add your actual notification sending logic here
                            // if it's meant to be triggered from the drawer.
                        }
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                TopBar(
                                    onThemeToggle = { isDarkTheme.value = !isDarkTheme.value },
                                    onOpenDrawer = { scope.launch { drawerState.open() } }
                                )
                            },
                            bottomBar = { BottomNavigationBar(navController) }
                        ) { innerPadding ->
                            NavHost(
                                navController = navController,
                                startDestination = "home",
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                composable("home") { HomeScreen(navController, context = LocalContext.current) }
                                composable("events") { SubscribedEventsScreen(navController) }
                                composable("favorites") { FavoritesScreen(navController) }
                                composable("eventDetails/{eventId}") { backStackEntry ->
                                    val eventId = backStackEntry.arguments?.getString("eventId")
                                    EventDetailsScreen(eventId = eventId)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}