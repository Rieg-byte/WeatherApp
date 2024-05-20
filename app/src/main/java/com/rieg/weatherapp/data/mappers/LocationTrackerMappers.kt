package com.rieg.weatherapp.data.mappers

import com.rieg.weatherapp.data.models.LocationCoordinateDTO
import com.rieg.weatherapp.domain.models.LocationCoordinate

fun LocationCoordinate.toLocationDTO(): LocationCoordinateDTO =
    LocationCoordinateDTO(
        latitude = latitude,
        longitude = longitude
    )


fun LocationCoordinateDTO.toLocationCoordinate(): LocationCoordinate =
    LocationCoordinate(
        latitude = latitude,
        longitude = longitude
    )