package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currently (
	val time : Int,
	val summary : String,
	val icon : String,
	val temperature : Double,
)