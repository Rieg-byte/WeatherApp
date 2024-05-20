package com.rieg.weatherapp.domain.models

data class WeatherInfo(
    val latitude: Double,
    val longitude: Double,
    val currentWeather: CurrentWeather,
    val weeklyWeather: List<DayWeather>
)
