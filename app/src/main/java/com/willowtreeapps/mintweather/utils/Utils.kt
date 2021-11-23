package com.willowtreeapps.mintweather.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.willowtreeapps.mintweather.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/*
* For list of condition codes visit https://developer.yahoo.com/weather/documentation.html
* */
fun getConditionsDrawable(conditionCode: Int): Int = when (conditionCode) {
    in listOf(32, 34, 36, 44) -> R.drawable.sun
    in listOf(31, 33) -> R.drawable.crescent_moon
    in listOf(27, 29) -> R.drawable.cloudy_moon
    in listOf(28, 30) -> R.drawable.cloudy_day
    26 -> R.drawable.cloudy
    in listOf(11, 12, 39, 40, 45) -> R.drawable.rain
    in listOf(3, 4, 37, 38, 47) -> R.drawable.thunderstorm
    in listOf(5, 6, 7, 8, 9, 10, 17, 18, 35) -> R.drawable.sleet
    in listOf(13, 14, 16, 41, 46) -> R.drawable.snow
    in listOf(15, 42, 43) -> R.drawable.blizzard
    25 -> R.drawable.freezing
    36 -> R.drawable.hot_temperature
    24 -> R.drawable.wind
    0 -> R.drawable.tornado
    in listOf(1, 2) -> R.drawable.hurricane
    in listOf(19, 20, 21, 22, 23) -> R.drawable.fog
    else -> R.drawable.clear_sky
}

/*
* For list of icon codes visit https://www.dr-lex.be/software/darksky-icons.html
* */
fun getConditionsDrawable(conditionCode: String): Int = when (conditionCode) {
    "clear-day" -> R.drawable.sun
    "clear-night" -> R.drawable.crescent_moon
    "partly-cloudy-night" -> R.drawable.cloudy_moon
    "partly-cloudy-day" -> R.drawable.cloudy_day
    "cloudy" -> R.drawable.cloudy
    "rain" -> R.drawable.rain
    "sleet" -> R.drawable.sleet
    "snow" -> R.drawable.snow
    "wind" -> R.drawable.wind
    "fog" -> R.drawable.fog
    else -> R.drawable.clear_sky
}

@RequiresApi(Build.VERSION_CODES.O)
fun getHourFormattedWithZone(time: Long, zoneId: String): String {
    val instant = Instant.ofEpochSecond(time)
    val formatter = DateTimeFormatter.ofPattern("h a")
    val hour = LocalDateTime.ofInstant(instant, ZoneId.of(zoneId)).format(formatter)
    return hour.toString()
}