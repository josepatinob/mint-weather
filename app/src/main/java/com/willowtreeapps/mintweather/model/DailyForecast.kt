package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecast(
    val day: String,
    val date: Long,
    val low: Int,
    val high: Int,
    val text: String,
    val code: Int
)