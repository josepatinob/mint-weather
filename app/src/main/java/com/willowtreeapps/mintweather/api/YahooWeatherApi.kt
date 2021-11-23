package com.willowtreeapps.mintweather.api

import com.willowtreeapps.mintweather.model.YahooWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YahooWeatherApi {

    companion object {
        const val BASE_URL = "https://yahoo-weather5.p.rapidapi.com/"
    }

    @Headers(
        "x-rapidapi-host: yahoo-weather5.p.rapidapi.com",
        "x-rapidapi-key: 726c28372dmsh963eae85d778ccap1d628ajsnaa2049ec9ecf"
    )
    @GET("weather")
    suspend fun getYahooWeatherForecast(
        @Query("lat") latitude: String? = "35.934987",
        @Query("long") longitude: String? = "-78.945926"
    ): YahooWeatherResponse
}