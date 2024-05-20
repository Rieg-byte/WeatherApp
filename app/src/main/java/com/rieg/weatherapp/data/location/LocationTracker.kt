package com.rieg.weatherapp.data.location

import com.rieg.weatherapp.data.models.LocationCoordinateDTO

interface LocationTracker {
    suspend fun getCurrentLocation(): LocationCoordinateDTO
    suspend fun getNameLocation(locationCoordinateDTO: LocationCoordinateDTO): String
}