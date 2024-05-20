package com.rieg.weatherapp.domain.repository

import com.rieg.weatherapp.domain.models.WeatherInfo

interface WeatherRepository{
    suspend fun getWeatherInfo(latitude: Double, altitude: Double): WeatherInfo
}
