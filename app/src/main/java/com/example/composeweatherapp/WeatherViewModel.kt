package com.example.composeweatherapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_homeworks_2k2s.data.response.DetailModel
import com.example.composeweatherapp.service.City
import com.example.composeweatherapp.service.CityService
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val cityService = CityService()
    private val _errorMessage = mutableStateOf("")
    private val _cityList = listOf<City>().toMutableStateList()
    private val _currentCityWeather = mutableStateOf<DetailModel?>(null)


    val errorMessage: String
        get() = _errorMessage.value
    val cityList: List<City>
        get() = _cityList
    val currentCityWeather: DetailModel?
        get() = _currentCityWeather.value

    fun getCityList(lat: Double, lon: Double, cnt: Int) {
        viewModelScope.launch {
            try {
                cityService.getNearCities(lat, lon, cnt)?.let { _cityList.addAll(it.toList()) }
            } catch(e: Exception) {
                _errorMessage.value = e.message?.toString() ?: "Something went wrong"
            }
        }
    }

    fun getCityWeather(id: Int) {
        viewModelScope.launch {
            try {
                cityService.getCityWeather(id)?.let { _currentCityWeather.value = it}
            } catch(e: Exception) {
                _errorMessage.value = e.message?.toString() ?: "Something went wrong"
            }
        }
    }
}
