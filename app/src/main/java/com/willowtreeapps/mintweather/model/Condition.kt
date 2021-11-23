package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String,
    val code: Int,
    val temperature: Int
)