package com.rieg.weatherapp.di.modules

import com.rieg.weatherapp.data.location.LocationTracker
import com.rieg.weatherapp.data.location.LocationTrackerImpl
import com.rieg.weatherapp.data.repository.LocationRepositoryImpl
import com.rieg.weatherapp.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    abstract fun provideLocationRepository(locationRepository: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun provideLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}