package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentObservation(
    val wind: Wind,
    val atmosphere: Atmosphere,
    val astronomy: Astronomy,
    val condition: Condition,
    val pubDate: Int
)