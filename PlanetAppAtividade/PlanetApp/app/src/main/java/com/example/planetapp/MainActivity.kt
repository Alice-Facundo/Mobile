package com.example.planetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.planetapp.ui.navigation.NavGraph
import com.example.planetapp.ui.theme.PlanetAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetAppTheme {
                NavGraph(
                    onSettingsClick = {
                        // Ação para Configurações (ex: abrir tela de configurações)
                    },
                    onHelpClick = {
                        // Ação para Ajuda (ex: abrir tela de ajuda)
                    }
                )
            }
        }
    }
}
