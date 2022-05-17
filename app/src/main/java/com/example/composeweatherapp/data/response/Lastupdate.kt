package com.example.composeweatherapp.data.response

import com.google.gson.annotations.SerializedName

data class Lastupdate(
    @SerializedName("valueL")
    val valueL: Int,
)
