package com.willowtreeapps.mintweather.api

import com.willowtreeapps.mintweather.model.DarkSkyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DarkSkyApi {

    companion object {
        const val BASE_URL = "https://dark-sky.p.rapidapi.com/"
    }

    /* API KEY HAS BEEN REVOKED */
    @Headers(
        "x-rapidapi-host: dark-sky.p.rapidapi.com",
        "x-rapidapi-key: 726c28372dmsh963eae85d778ccap1d628ajsnaa2049ec9ecf"
    )
    @GET("{location}")
    suspend fun getWeatherForecast(
        @Path("location", encoded = true) location: String = "35.934987,-78.945926",
        @Query("exclude") exclude: String = "minutely,flags"
    ): DarkSkyWeatherResponse
}