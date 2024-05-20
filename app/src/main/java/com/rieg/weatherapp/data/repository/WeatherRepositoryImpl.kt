package com.rieg.weatherapp.data.repository

import android.icu.util.TimeZone
import com.rieg.weatherapp.data.mappers.toWeatherInfo
import com.rieg.weatherapp.data.network.WeatherRemoteDataSource
import com.rieg.weatherapp.domain.models.WeatherInfo
import com.rieg.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
): WeatherRepository {
    override suspend fun getWeatherInfo(
        latitude: Double,
        altitude: Double
    ): WeatherInfo = weatherRemoteDataSource
            .getWeatherData(latitude, altitude, timezone = TimeZone.getDefault().id)
            .toWeatherInfo()
}