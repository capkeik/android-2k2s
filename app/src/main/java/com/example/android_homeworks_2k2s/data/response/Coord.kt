package com.example.android_homeworks_2k2s.data.response

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
