package com.example.nighteventsapp.ui.screens

import androidx.compose.foundation.Image // Import for Image composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column // Import for Column composable
import androidx.compose.foundation.layout.Row // Import for Row composable
import androidx.compose.foundation.layout.Spacer // Import for Spacer composable
import androidx.compose.foundation.layout.fillMaxWidth // Import for fillMaxWidth modifier
import androidx.compose.foundation.layout.height // Import for height modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size // Import for size modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Import for items in LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults // Import for CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale // Import for ContentScale
import androidx.compose.ui.res.painterResource // Import for painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nighteventsapp.models.eventList

@Composable
fun SubscribedEventsScreen(navController: NavHostController) {
    // Assuming 'isSubscribed' is a MutableState<Boolean> property in your Event model
    val subscribedEvents = eventList.filter { it.isSubscribed.value }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        if (subscribedEvents.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não está inscrito em nenhum evento.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(subscribedEvents) { event -> // Use items for list of items
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth() // Make card fill width
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
                                text = event.title, // <--- Corrected from event.title to event.name
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = event.date,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = event.location,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.description,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2 // Limita o texto a 2 linhas
                            )
                        }
                    }
                }
            }
        }
    }
}