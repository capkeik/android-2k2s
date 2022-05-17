package com.example.composeweatherapp.service

import com.example.composeweatherapp.data.response.ListModel

class CityListMapper {
    private val cityMapper = CityMapper()
    fun map(listModel: ListModel) : MutableList<City>{
        val result = mutableListOf<City>()
        listModel.list.forEach {
            result.add(cityMapper.map(it))
        }
        return result
    }
}
