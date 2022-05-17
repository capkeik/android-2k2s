package com.example.composeweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composeweatherapp.composables.CityListScreen
import com.example.composeweatherapp.const.CITY_COUNT
import com.example.composeweatherapp.const.DEFAULT_LATITUDE
import com.example.composeweatherapp.const.DEFAULT_LONGITUDE
import com.example.composeweatherapp.data.request.ApiService
import com.example.composeweatherapp.navigation.WeatherApp

class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val latitude = DEFAULT_LATITUDE
    private val longitude = DEFAULT_LONGITUDE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.getCityList(latitude, longitude, CITY_COUNT)
        setContent {
            CityListScreen(navController = null)
        }
    }
}
