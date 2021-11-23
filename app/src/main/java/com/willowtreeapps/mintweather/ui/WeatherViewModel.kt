package com.willowtreeapps.mintweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtreeapps.mintweather.model.DarkSkyWeatherResponse
import com.willowtreeapps.mintweather.model.YahooWeatherResponse
import com.willowtreeapps.mintweather.repository.DarkSkyRepository
import com.willowtreeapps.mintweather.repository.YahooWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val darkSkyRepository: DarkSkyRepository,
    private val yahooWeatherRepository: YahooWeatherRepository
) : ViewModel() {

    private var _weatherForecast = MutableLiveData<DarkSkyWeatherResponse>()
    val weatherForecast: LiveData<DarkSkyWeatherResponse> get() = _weatherForecast

    private var _yahooWeatherForecast = MutableLiveData<YahooWeatherResponse>()
    val yahooWeatherForecast: LiveData<YahooWeatherResponse> get() = _yahooWeatherForecast

    fun getWeatherForecasts(latitude: String, longitude: String) {
        getYahooWeatherForecast(latitude, longitude)
        getCurrentWeatherForecast(latitude, longitude)
    }

    private fun getYahooWeatherForecast(latitude: String, longitude: String) =
        viewModelScope.launch {
            try {
                val weather = yahooWeatherRepository.getYahooWeatherForecast(
                    latitude, longitude
                )
                Log.d("TESTING", weather.toString())
                _yahooWeatherForecast.value = weather
            } catch (e: Exception) {
                Log.e("TESTING", e.toString())
            }
        }

    private fun getCurrentWeatherForecast(latitude: String, longitude: String) =
        viewModelScope.launch {
            try {
                val weather = darkSkyRepository.getWeatherForecast(
                    latitude, longitude
                )
                Log.d("TESTING", weather.toString())
                _weatherForecast.value = weather
            } catch (e: Exception) {
                Log.e("TESTING", e.toString())
            }
        }
}