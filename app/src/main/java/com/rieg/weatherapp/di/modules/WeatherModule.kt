package com.rieg.weatherapp.di.modules

import com.rieg.weatherapp.data.network.WeatherRemoteDataSource
import com.rieg.weatherapp.data.network.WeatherRemoteDataSourceImpl
import com.rieg.weatherapp.data.repository.WeatherRepositoryImpl
import com.rieg.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {
    @Binds
    abstract fun provideWeatherRemoteDataSource(
        weatherRemoteDataSource: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource

    @Binds
    abstract fun provideWeatherRepository(
        weatherRepository: WeatherRepositoryImpl
    ): WeatherRepository
}