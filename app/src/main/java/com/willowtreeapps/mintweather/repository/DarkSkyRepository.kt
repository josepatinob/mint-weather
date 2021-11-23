package com.willowtreeapps.mintweather.repository

import com.willowtreeapps.mintweather.api.DarkSkyApi
import com.willowtreeapps.mintweather.model.DarkSkyWeatherResponse
import javax.inject.Inject

class DarkSkyRepository @Inject constructor(
    private val darkSkyApi: DarkSkyApi
) {

    suspend fun getWeatherForecast(latitude: String, longitude: String): DarkSkyWeatherResponse {
        return darkSkyApi.getWeatherForecast(
            location = listOf(
                latitude,
                longitude
            ).joinToString(",")
        )
    }
}