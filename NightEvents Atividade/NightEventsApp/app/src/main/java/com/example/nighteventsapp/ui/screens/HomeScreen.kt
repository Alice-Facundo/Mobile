package com.example.nighteventsapp.ui.screens

import android.Manifest // Import for Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager // Import for PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult // Import for rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts // Import for ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect // Import for LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext // Import for LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat // Import for ContextCompat
import androidx.navigation.NavHostController
import com.example.nighteventsapp.models.eventList

@Composable
fun HomeScreen(navController: NavHostController, context: Context) {
    val localContext = LocalContext.current // Get the current context

    // Launcher for requesting POST_NOTIFICATIONS permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, you can now post notifications
            // (You might want to show a toast or log this)
        } else {
            // Permission denied, handle accordingly (e.g., show a message to the user)
            // (You might want to show a toast or log this)
        }
    }

    // Request permission when the Composable is first launched (or when context changes, though less likely)
    // Only request on Android 13 (API 33) and above
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // TIRAMISU is API 33
            val notificationPermissionStatus = ContextCompat.checkSelfPermission(
                localContext,
                Manifest.permission.POST_NOTIFICATIONS
            )
            if (notificationPermissionStatus != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // Cria o canal de notificação
    createNotificationChannel(context)
    Column {
        // Seção de eventos inscritos no estilo LazyRow
        val subscribedEvents = eventList.filter { it.isSubscribed.value }
        if (subscribedEvents.isNotEmpty()) {
            Text(
                text = "Eventos Inscritos",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
            )
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(subscribedEvents) { event ->
                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                navController.navigate("eventDetails/${event.id}")
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = event.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Seção principal com a lista de todos os eventos
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(eventList) { event ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("eventDetails/${event.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Imagem do evento
                            Image(
                                painter = painterResource(id = event.imageRes),
                                contentDescription = event.title,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = event.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = event.location,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                            // Ícone de favorito
                            Icon(
                                imageVector = if (event.isFavorite.value) Icons.Default.Favorite else
                                    Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        event.isFavorite.value = !event.isFavorite.value
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Descrição do evento
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Botão "Se Inscrever" ou "Inscrito"
                        Button(
                            onClick = {
                                event.isSubscribed.value = !event.isSubscribed.value
                                if (event.isSubscribed.value) {
                                    // Check permission again before sending notification
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                                        ContextCompat.checkSelfPermission(
                                            localContext,
                                            Manifest.permission.POST_NOTIFICATIONS
                                        ) != PackageManager.PERMISSION_GRANTED
                                    ) {
                                        // If permission is not granted, request it.
                                        // The notification won't be sent immediately,
                                        // but the user will be prompted.
                                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    } else {
                                        // Permission is granted or not needed (below API 33)
                                        sendNotification(context, event.title)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (event.isSubscribed.value) "Inscrito" else "Se Inscrever"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Botão "Ver mais sobre"
                        Button(
                            onClick = {
                                navController.navigate("eventDetails/${event.id}")
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Ver mais sobre ${event.title}")
                        }
                    }
                }
            }
        }
    }
}

// Função para criar o canal de notificação
private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Inscrição de Eventos"
        val descriptionText = "Canal para notificações de inscrição em eventos"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("EVENT_CHANNEL", name,
            importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

// Função para enviar a notificação
private fun sendNotification(context: Context, eventTitle: String) {
    val builder = NotificationCompat.Builder(context, "EVENT_CHANNEL")
        .setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("Inscrição Confirmada")
        .setContentText("Você foi inscrito no evento: $eventTitle")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        // Ensure permission is checked before calling notify if context is relevant for API 33+
        // However, the check is now performed in the onClick lambda, so this call should be safe.
        notify(eventTitle.hashCode(), builder.build())
    }
}