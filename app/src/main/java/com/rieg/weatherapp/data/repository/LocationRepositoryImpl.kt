package com.rieg.weatherapp.data.repository

import com.rieg.weatherapp.data.location.LocationTracker
import com.rieg.weatherapp.data.mappers.toLocationCoordinate
import com.rieg.weatherapp.data.mappers.toLocationDTO
import com.rieg.weatherapp.domain.models.LocationCoordinate
import com.rieg.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationTracker: LocationTracker,
): LocationRepository {
    override suspend fun getCurrentLocation(): LocationCoordinate = locationTracker.getCurrentLocation().toLocationCoordinate()

    override suspend fun getNameLocation(
        locationCoordinate: LocationCoordinate
    ): String = locationTracker.getNameLocation(locationCoordinate.toLocationDTO())
}