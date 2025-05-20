package com.example.planetapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.planetapp.models.Planet

@Composable
fun PlanetListItem(
    planet: Planet,
    onPlanetSelected: (Planet) -> Unit,
    onFavoriteToggle: (Planet) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = planet.imageRes),
                    contentDescription = "${planet.name} Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = planet.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Galaxy: ${planet.galaxy}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { onFavoriteToggle(planet) }) {
                    Icon(
                        imageVector = if (planet.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (planet.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text("Type: ${planet.type}")
                Text("Distance from Sun: ${planet.distanceFromSun}")
                Text("Diameter: ${planet.diameter}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = planet.characteristics,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onPlanetSelected(planet) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Ver mais sobre ${planet.name}")
            }
        }
    }
}
