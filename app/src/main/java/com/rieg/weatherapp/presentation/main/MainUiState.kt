package com.rieg.weatherapp.presentation.main

import com.rieg.weatherapp.domain.models.WeatherInfo

sealed interface MainUiState {
    data object Error: MainUiState
    data object Loading: MainUiState
    data class Success(
        val location: String,
        val weatherInfo: WeatherInfo
    ) : MainUiState
}