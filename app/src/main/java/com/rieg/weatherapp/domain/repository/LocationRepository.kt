package com.rieg.weatherapp.domain.repository

import com.rieg.weatherapp.domain.models.LocationCoordinate

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationCoordinate
    suspend fun getNameLocation(locationCoordinate: LocationCoordinate): String
}