package com.rieg.weatherapp.data.mappers

import com.rieg.weatherapp.data.models.WeatherDataDTO
import com.rieg.weatherapp.domain.models.CurrentWeather
import com.rieg.weatherapp.domain.models.DayWeather
import com.rieg.weatherapp.domain.models.TypeWeather
import com.rieg.weatherapp.domain.models.WeatherInfo

fun WeatherDataDTO.toWeatherInfo(): WeatherInfo {
    val dailyWeather: List<DayWeather> = dailyWeatherDto.time.mapIndexed { index, time ->
        DayWeather(
            time = time,
            temperatureMax = dailyWeatherDto.temperatureMax[index],
            temperatureMin = dailyWeatherDto.temperatureMin[index]
        )
    }
    val currentWeather = CurrentWeather(
        temperature = currentWeatherDto.temperature,
        typeWeather = TypeWeather.getTypeWeatherFromCode(currentWeatherDto.weatherCode)
    )
    return WeatherInfo(
        latitude = latitude,
        longitude = longitude,
        currentWeather = currentWeather,
        weeklyWeather = dailyWeather
    )
}
