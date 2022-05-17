package com.example.composeweatherapp.data.response

import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("list")
    val list: List<DetailModel>,
)
