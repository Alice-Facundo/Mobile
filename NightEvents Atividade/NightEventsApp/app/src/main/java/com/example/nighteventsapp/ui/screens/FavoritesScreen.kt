package com.example.nighteventsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column // Ensure Column is imported
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size // Import for .size() modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Import for items in LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale // Import for ContentScale
import androidx.compose.ui.res.painterResource // Import for painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nighteventsapp.models.eventList
import androidx.compose.foundation.Image // Import for Image composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon

@Composable
fun FavoritesScreen(navController: NavHostController) {
    // Observa as mudanças no estado de favoritos dinamicamente
    val favoriteEvents = eventList.filter { it.isFavorite.value } // Assuming isFavorite is a mutableStateOf boolean

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        if (favoriteEvents.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não tem eventos favoritos.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(favoriteEvents) { event -> // Use items for list of items
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth() // Added fillMaxWidth to make cards take full width
                        .clickable {
                            navController.navigate("eventDetails/${event.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Exibe a imagem do evento
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = "Imagem do evento",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 16.dp)
                        )
                        // Exibe os detalhes do evento
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = event.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                            Text(
                                text = event.description,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 2 // Limit description to 2 lines
                            )
                            // You can add more event details here, e.g., date, location

                            Icon(
                                imageVector = if (event.isFavorite.value)
                                    Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        // Alterna o estado de favorito
                                        event.isFavorite.value =
                                            !event.isFavorite.value
                                    }
                            )
                        }
                    }
                }
            }
        }
    }