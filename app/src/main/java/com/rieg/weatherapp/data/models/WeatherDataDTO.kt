package com.rieg.weatherapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataDTO(
    val latitude: Double,
    val longitude: Double,
    @SerialName("current")
    val currentWeatherDto: CurrentWeatherDTO,
    @SerialName("daily")
    val dailyWeatherDto: DailyWeatherDTO
)