package com.example.composeweatherapp.data.request

import com.example.android_homeworks_2k2s.data.response.DetailModel
import com.example.composeweatherapp.data.response.ListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherList(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("cnt") count:Int
    ): ListModel

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("id") id: Int) : DetailModel
}
