package com.example.penziapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.penziapp.ui.screens.HomeScreen
import com.example.penziapp.ui.screens.LoginScreen

/** Contains the composable which displays the contents on the screen such as the top
bar and home/login screen composable */

@Composable
fun AdminDashboardApp(){
    val navController = rememberNavController()

    NavHost(navController, "home") {
        composable("home"){
            HomeScreen(nav = navController)
        }
        composable("login"){
            LoginScreen(nav = navController)
        }
    }
}