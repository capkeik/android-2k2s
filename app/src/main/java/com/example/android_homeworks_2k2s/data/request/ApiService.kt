package com.example.android_homeworks_2k2s.data.request

import com.example.android_homeworks_2k2s.DEFAULT_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private const val QUERY_KEY = "appid"
    private const val API_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "1f5d45a434cbc8433fb588f559d8c9f9"
    private const val timeout = DEFAULT_TIMEOUT

    val weatherApi: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    private val interceptor =
        Interceptor{
                chain ->
        val originalRequest = chain.request()
        originalRequest.url.newBuilder()
            .addQueryParameter(QUERY_KEY, API_KEY)
            .build()
            .let {
                chain.proceed(
                originalRequest.newBuilder().url(it).build()
                ) }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
