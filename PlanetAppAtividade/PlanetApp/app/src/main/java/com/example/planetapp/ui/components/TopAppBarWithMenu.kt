package com.example.planetapp.ui.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource

@ExperimentalMaterial3Api
@Composable
fun TopAppBarWithMenu(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.Top) {
                Image(
                    painter = painterResource(id = R.drawabl),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "PlanetApp",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        actions = {
            IconButton (onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons .Default .MoreVert,
                    contentDescription = "Menu"
                )
            }
            DropdownMenu (
                expanded = expanded ,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem (
                    text = { Text("Configurações" ) },
                    onClick = {
                        expanded = false
                        onSettingsClick ()

                    }
                )
                DropdownMenuItem (
                    text = { Text("Ajuda" ) },
                    onClick = {
                        expanded = false
                        onHelpClick ()

                    }
                )
            }
        },
        colors = TopAppBarDefaults .topAppBarColors (
            containerColor = MaterialTheme .colorScheme .primary ,
            titleContentColor = MaterialTheme .colorScheme .onPrimary ,
            actionIconContentColor = MaterialTheme .colorScheme .onPrimary
        )
    )
}