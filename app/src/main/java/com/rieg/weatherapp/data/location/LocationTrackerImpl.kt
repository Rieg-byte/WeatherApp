package com.rieg.weatherapp.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.rieg.weatherapp.data.models.LocationCoordinateDTO
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val DEFAULT_LATITUDE = 55.75
private const val DEFAULT_LONGITUDE =  37.61

class LocationTrackerImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val application: Application
): LocationTracker {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LocationCoordinateDTO {
        if (isPermissionsGranted() && isLocationEnabled()) {
            val currentLocation = fusedLocationProviderClient.lastLocation.await()
            return LocationCoordinateDTO(
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude
            )
        } else {
            return LocationCoordinateDTO(
                latitude = DEFAULT_LATITUDE,
                longitude = DEFAULT_LONGITUDE
            )
        }
    }

    override suspend fun getNameLocation(locationCoordinateDTO: LocationCoordinateDTO): String{
        var addressString = ""
        val addressList: MutableList<Address>? = geocoder.getFromLocation(locationCoordinateDTO.latitude, locationCoordinateDTO.longitude, 1)
        if (!addressList.isNullOrEmpty()) {
            val address = addressList[0]
            addressString = address.locality ?: address.countryName
        }
        return addressString
    }

    private fun isPermissionsGranted(): Boolean {
        val hasAccessFineLocation =
            ContextCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocation =
            ContextCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        return hasAccessCoarseLocation && hasAccessFineLocation
    }

    private fun isLocationEnabled(): Boolean {
        val locationTracker: LocationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationTracker.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationTracker.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
        return isGpsEnabled
    }
}