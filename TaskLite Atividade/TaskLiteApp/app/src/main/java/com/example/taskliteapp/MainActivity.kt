package com.example.taskliteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel // CORRECTED: Import for viewModel() composable function
import com.example.taskliteapp.ui.DesafioTaskListScreen // Import for DesafioTaskListScreen
import com.example.taskliteapp.ui.theme.TaskLiteAppTheme
import com.example.taskliteapp.viewmodel.DesafioTaskViewModel // Import your ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskLiteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Get an instance of your ViewModel using the viewModel() composable
                    val taskViewModel: DesafioTaskViewModel = viewModel() // CORRECTED: Use viewModel() with lowercase 'v'

                    // Call your main task list screen
                    DesafioTaskListScreen(
                        viewModel = taskViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

