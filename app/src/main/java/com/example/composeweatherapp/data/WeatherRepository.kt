package com.example.composeweatherapp.data

import com.example.composeweatherapp.data.request.WeatherApi
import com.example.composeweatherapp.data.response.DetailModel
import com.example.composeweatherapp.const.DEFAULT_TIMEOUT
import com.example.composeweatherapp.data.response.ListModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



private const val QUERY_KEY = "appid"
private const val API_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "1f5d45a434cbc8433fb588f559d8c9f9"
private const val TIMEOUT = DEFAULT_TIMEOUT


object WeatherRepository {
    private val interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        originalRequest
            .url
            .newBuilder()
            .addQueryParameter(QUERY_KEY, API_KEY)
            .build()
            .let {
                chain.proceed(
                    originalRequest.newBuilder().url(it).build()
                )
            }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private val weatherApi by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    suspend fun getNearCitiesWeather(lat: Double, lon: Double, cnt: Int) : ListModel {
        return weatherApi.getWeatherList(lat, lon, cnt)
    }

    suspend fun getCityWeatherById(id: Int) : DetailModel {
        return weatherApi.getWeather(id)
    }
}
