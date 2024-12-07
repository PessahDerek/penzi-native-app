package com.example.penziapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.penziapp.ui.AdminDashboardApp
import com.example.penziapp.ui.theme.PenziAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Initialize AppPreferences
        AppPreferences.setup(applicationContext)
        setContent {
            PenziAppTheme {
                AdminDashboardApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PenziAppTheme {
        AdminDashboardApp()
    }
}