package com.example.composeweatherapp.service

import com.example.composeweatherapp.data.response.DetailModel
import com.example.composeweatherapp.data.WeatherRepository

class CityService {
    private val repository =  WeatherRepository
    private val mapper = CityListMapper()

    suspend fun getNearCities(
        lat: Double,
        lon: Double,
        cnt: Int
    ): MutableList<City> {
        return repository.getNearCitiesWeather(lat, lon, cnt)
            .let { mapper.map(it) }
    }

    suspend fun getCityWeather(id: Int) : DetailModel? {
        return repository.getCityWeatherById(id)
    }
}
