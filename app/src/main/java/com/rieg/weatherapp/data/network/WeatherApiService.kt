package com.rieg.weatherapp.data.network

import com.rieg.weatherapp.data.models.WeatherDataDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast?current=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String
    ): WeatherDataDTO
}