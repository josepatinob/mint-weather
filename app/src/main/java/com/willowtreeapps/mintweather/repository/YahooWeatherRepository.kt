package com.willowtreeapps.mintweather.repository

import com.willowtreeapps.mintweather.api.YahooWeatherApi
import com.willowtreeapps.mintweather.model.YahooWeatherResponse
import javax.inject.Inject

class YahooWeatherRepository @Inject constructor(
    private val yahooWeatherApi: YahooWeatherApi
) {

    suspend fun getYahooWeatherForecast(latitude: String, longitude: String): YahooWeatherResponse {
        return yahooWeatherApi.getYahooWeatherForecast(latitude, longitude)
    }
}