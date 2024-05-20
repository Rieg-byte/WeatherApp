package com.rieg.weatherapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rieg.weatherapp.domain.repository.LocationRepository
import com.rieg.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
): ViewModel() {
    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        loadWeatherInfo()
    }

    fun loadWeatherInfo() = viewModelScope.launch {
        try {
            _mainUiState.value = MainUiState.Loading
            val currentLocation = locationRepository.getCurrentLocation()
            val nameLocation = locationRepository.getNameLocation(currentLocation)
            val data = weatherRepository.getWeatherInfo(currentLocation.latitude, currentLocation.longitude)
            _mainUiState.value = MainUiState.Success(
                location = nameLocation,
                weatherInfo = data
            )
        } catch (e: Exception) {
            _mainUiState.value = MainUiState.Error
        }
    }

    fun onRefresh() {
        loadWeatherInfo()
    }
}