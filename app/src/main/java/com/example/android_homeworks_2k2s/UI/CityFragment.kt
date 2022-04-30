package com.example.android_homeworks_2k2s.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android_homeworks_2k2s.R
import com.example.android_homeworks_2k2s.data.request.ApiService
import com.example.android_homeworks_2k2s.data.response.DetailModel
import com.example.android_homeworks_2k2s.databinding.FragmentCityBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class CityFragment : Fragment(R.layout.fragment_city) {
    private lateinit var binding: FragmentCityBinding
    private var city: String? = null
    private val api = ApiService.weatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString("CITY_NAME")
        }
        city?.let {
            initWeather(it)
        }
    }
    private fun initWeather(cityTitle: String) {
        lifecycleScope.launch {
            weatherView(api.getWeather(cityTitle))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityBinding.bind(view)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun weatherView(cityWeather: DetailModel) {
        with(binding) {
            tvName.text = cityWeather.name
            tvTemp.text = context?.resources?.getString(
            R.string.temp,
            cityWeather.main.temp.toInt().toString()
            )
            tvTempMin.text = context?.resources?.getString(
                R.string.temp_min,
                cityWeather.main.temp.toInt().toString()
            )
            tvTempMax.text = context?.resources?.getString(
                R.string.temp_max,
                cityWeather.main.temp.toInt().toString()
            )
            tvFeelsLike.text = context?.resources?.getString(
                R.string.feels_like,
                cityWeather.main.feelsLike.toInt().toString()
            )
            tvDirection.text = def_direction(cityWeather.wind.degree)
            tvWind.text = context?.resources?.getString(
                R.string.weather_speed,
                cityWeather.wind.speed.toString()
            )
            tvPressure.text = context?.resources?.getString(
                R.string.pressure_mm,
                (cityWeather.main.pressure/1.333).toInt().toString()
            )
            tvHumidity.text = cityWeather.main.humidity.toString() + "%"
            tvSunrise.text = SimpleDateFormat("HH:mm").format(cityWeather.sys.sunrise * 1000)
            tvSunset.text = SimpleDateFormat("HH:mm").format(cityWeather.sys.sunset * 1000)
        }
    }
    private fun def_direction(degree : Int) : String {
        return when (degree) {
            in 0 until 23 -> "N"
            in 24 until 68 -> "N-E"
            in 69 until 113 -> "E"
            in 114 until 158 -> "S-E"
            in 159 until 203 -> "S"
            in 204 until 248 -> "S-W"
            in 249 until 293 -> "S"
            in 294 until 338 -> "N-W"
            in 339 until 361 -> "N"
            else -> "-"
        }
    }
}

