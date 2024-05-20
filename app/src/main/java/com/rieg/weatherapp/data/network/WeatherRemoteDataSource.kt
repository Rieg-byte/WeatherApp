package com.rieg.weatherapp.data.network

import com.rieg.weatherapp.data.models.WeatherDataDTO

interface WeatherRemoteDataSource {
    suspend fun getWeatherData(latitude: Double, longitude: Double, timezone: String): WeatherDataDTO
}