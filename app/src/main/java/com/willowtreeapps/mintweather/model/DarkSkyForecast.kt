package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DarkSkyForecast(
    val time: Long,
    val summary: String,
    val icon: String,
    val sunriseTime: Int = -1,
    val sunsetTime: Int = -1,
    val temperature: Double = -1.0,
    val temperatureHigh: Double = -1.0,
    val temperatureHighTime: Long = -1,
    val temperatureLow: Double = -1.0,
    val temperatureLowTime: Long = -1,
    val temperatureMin: Double = -1.0,
    val temperatureMax: Double = -1.0,
    var timezone: String = ""
)