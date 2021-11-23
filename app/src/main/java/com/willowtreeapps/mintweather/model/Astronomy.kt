package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Astronomy(
    val sunrise: String,
    val sunset: String
)