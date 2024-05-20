package com.rieg.weatherapp.data.network

import com.rieg.weatherapp.data.models.WeatherDataDTO
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(private val weatherApiService: WeatherApiService): WeatherRemoteDataSource {
    override suspend fun getWeatherData(latitude: Double, longitude: Double, timezone: String): WeatherDataDTO {
        return weatherApiService.getWeatherData(latitude, longitude, timezone)
    }
}