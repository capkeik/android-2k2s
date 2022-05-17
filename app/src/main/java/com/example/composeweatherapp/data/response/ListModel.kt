package com.example.composeweatherapp.data.response

import com.example.android_homeworks_2k2s.data.response.DetailModel
import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("list")
    val list: List<DetailModel>,
)
