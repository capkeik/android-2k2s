package com.example.composeweatherapp.service

import com.example.composeweatherapp.data.response.DetailModel

class CityMapper {
    fun map(detailModel: DetailModel) : City = City(
        detailModel.name,
        detailModel.main.temp,
        detailModel.id
    )
}
