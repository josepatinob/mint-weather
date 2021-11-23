package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val chill: Int,
    val direction: Int,
    val speed: Double
)