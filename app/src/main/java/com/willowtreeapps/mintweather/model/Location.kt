package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val woeid: Int,
    val city: String,
    val region: String,
    val country: String,
    val lat: Double,
    val long: Double,
    val timezone_id: String
)