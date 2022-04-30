package com.example.android_homeworks_2k2s.UI

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android_homeworks_2k2s.*
import com.example.android_homeworks_2k2s.data.request.ApiService
import com.example.android_homeworks_2k2s.databinding.FragmentCityListBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.lang.Exception

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var recyclerView: RecyclerView? = null
    private var searchView: SearchView? = null
    private val api = ApiService.weatherApi
    private val latitude = DEFAULT_LATITUDE
    private val longitude = DEFAULT_LONGITUDE
    private val count = CITY_COUNT
    private lateinit var binding: FragmentCityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.also {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityListBinding.bind(view)
        recyclerView = binding.rvList
        searchView = binding.svCityName
        getLastLocation()
        searchCity()
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        checkPermissions()
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                    location->
                if (location != null) {
                    initRV(location.latitude, location.longitude)
                }
                else{
                    Snackbar.make(requireView(), "Your location is not available", Snackbar.LENGTH_SHORT).show()
                    initRV(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
                }
            }
    }

    private fun checkPermissions(){
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ){
            initRV(latitude, longitude)
        }
    }

    private fun initRV(latitude:Double, longitude:Double) {
            lifecycleScope.launch {
                recyclerView?.adapter = CityAdapter(
                    api.getWeatherList(latitude, longitude, count)
                ) { cityName ->
                    findNavController().navigate(
                        R.id.action_cityListFragment_to_cityFragment,
                        bundleOf("CITY_NAME" to cityName)
                    )
                }
            }
        }
    private fun searchCity(){
        searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String): Boolean {
                lifecycleScope.launch {
                    try {
                        api.getWeather(query)
                        findNavController().navigate(
                            R.id.action_cityListFragment_to_cityFragment,
                            bundleOf("CITY_NAME" to query)
                        )
                    }
                    catch (exception:Exception){
                        Snackbar.make(requireView(), "Couldn't find the city", Snackbar.LENGTH_SHORT).show()
                        searchView?.setQuery("", false)
                        searchView?.clearFocus()
                    }
                }
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }
}