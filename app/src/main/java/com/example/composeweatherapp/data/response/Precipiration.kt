package com.example.composeweatherapp.data.response

import com.google.gson.annotations.SerializedName

data class Precipiration(
    @SerializedName("valueP")
    val valueP: Double,
)
