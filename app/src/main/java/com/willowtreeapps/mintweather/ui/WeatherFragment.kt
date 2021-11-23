package com.willowtreeapps.mintweather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.willowtreeapps.mintweather.R
import com.willowtreeapps.mintweather.adapter.DailyForecastAdapter
import com.willowtreeapps.mintweather.adapter.HourlyForecastAdapter
import com.willowtreeapps.mintweather.databinding.WeatherFragmentBinding
import com.willowtreeapps.mintweather.utils.getConditionsDrawable
import dagger.hilt.android.AndroidEntryPoint

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()
    private val dailyForecastAdapter = DailyForecastAdapter()
    private val hourlyForecastAdapter = HourlyForecastAdapter()

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        requestLocation()

        with(binding) {
            viewModel.yahooWeatherForecast.observe(viewLifecycleOwner) { forecast ->
                // TODO: Add location either from api or from location services
                location.text = forecast.location.city
                currentTemp.text =
                    getString(
                        R.string.current_temp,
                        forecast.currentObservation.condition.temperature
                    )
                conditions.text = forecast.currentObservation.condition.text
                conditionsImage.setImageResource(getConditionsDrawable(forecast.currentObservation.condition.code))
                highAndLow.text = getString(
                    R.string.high_low_text,
                    forecast.forecasts[0].high,
                    forecast.forecasts[0].low
                )

                humidityPercent.text = "${forecast.currentObservation.atmosphere.humidity}%"
                sunrise.text = forecast.currentObservation.astronomy.sunrise
                sunset.text = forecast.currentObservation.astronomy.sunset

                dailyForecastList.adapter = dailyForecastAdapter
                dailyForecastAdapter.submitList(forecast.forecasts)
            }

            viewModel.weatherForecast.observe(viewLifecycleOwner) { forecast ->
                Log.d("WEATHER_F", forecast.toString())

                hourlySummary.text = forecast.hourly.summary

                hourlyForecastList.adapter = hourlyForecastAdapter
                val hourlyForecastList = forecast.hourly.data.slice(0..24)
                hourlyForecastList.map {
                    it.timezone = forecast.timezone
                }
                hourlyForecastAdapter.submitList(hourlyForecastList)
            }
        }

        return binding.root
    }

    private fun requestLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
            return
        }
        task.addOnSuccessListener { location: Location? ->
            if (location != null) {
                Log.d(
                    "WEATHER_FRAGMENT",
                    "lat: ${location.latitude} || long: ${location.longitude}"
                )
                viewModel.getWeatherForecasts(
                    location.latitude.toString(),
                    location.longitude.toString()
                )
            } else {
                Log.d("WEATHER_FRAGMENT", "LOCATION IS EMPTY!!")
            }
        }
    }

}

