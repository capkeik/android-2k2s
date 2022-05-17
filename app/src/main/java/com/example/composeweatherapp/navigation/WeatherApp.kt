package com.example.composeweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeweatherapp.composables.CityDetailsScreen
import com.example.composeweatherapp.composables.CityListScreen
import com.example.composeweatherapp.ui.theme.ComposeWeatherAppTheme

@Composable
fun WeatherApp() {
    ComposeWeatherAppTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                CityListScreen(navController)
            }
            composable("details") {
                CityDetailsScreen()
            }
        }
    }
}
