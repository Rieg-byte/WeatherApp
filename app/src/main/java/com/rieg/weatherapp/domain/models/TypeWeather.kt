package com.rieg.weatherapp.domain.models

import androidx.annotation.StringRes
import com.rieg.weatherapp.R


/**
 * /**
 *  * 0	Clear sky
 *  * 1, 2, 3	Mainly clear, partly cloudy, and overcast
 *  * 45, 48	Fog and depositing rime fog
 *  * 51, 53, 55	Drizzle: Light, moderate, and dense intensity
 *  * 56, 57	Freezing Drizzle: Light and dense intensity
 *  * 61, 63, 65	Rain: Slight, moderate and heavy intensity
 *  * 66, 67	Freezing Rain: Light and heavy intensity
 *  * 71, 73, 75	Snow fall: Slight, moderate, and heavy intensity
 *  * 77	Snow grains
 *  * 80, 81, 82	Rain showers: Slight, moderate, and violent
 *  * 85, 86	Snow showers slight and heavy
 *  * 95 *	Thunderstorm: Slight or moderate
 *  * 96, 99 *	Thunderstorm with slight and heavy hail
 *  */
 */
sealed class TypeWeather(
    @StringRes val type: Int,
) {
    data object ClearSky: TypeWeather(
        type = R.string.clear_sky
    )
    data object MainlyClear: TypeWeather(
        type = R.string.mainly_clear
    )
    data object PartlyCloud: TypeWeather(
        type = R.string.partly_cloudy
    )
    data object Overcast: TypeWeather(
        type = R.string.overcast
    )
    data object Fog: TypeWeather(
        type = R.string.fog
    )
    data object DepositingRimeFog: TypeWeather(
        type = R.string.depositing_rime_fog
    )
    data object DrizzleLight: TypeWeather(
        type = R.string.drizzle_light
    )
    data object DrizzleModerate: TypeWeather(
        type = R.string.drizzle_moderate
    )
    data object DrizzleDenseIntensity: TypeWeather(
        type = R.string.drizzle_dense_intensity
    )
    data object FreezingDrizzle: TypeWeather(
        type = R.string.freezing_drizzle_light
    )
    data object FreezingDrizzleDenseIntensity: TypeWeather(
        type = R.string.freezing_drizzle_dense_intensity
    )
    data object RainSlight: TypeWeather(
        type = R.string.rain_slight
    )
    data object RainModerate: TypeWeather(
        type = R.string.rain_moderate
    )
    data object RainHeavy: TypeWeather(
        type = R.string.rain_heavy
    )
    data object SnowfallSlight: TypeWeather(
        type = R.string.snowfall_slight
    )
    data object SnowfallModerate: TypeWeather(
        type = R.string.snowfall_moderate
    )
    data object SnowfallHeavy: TypeWeather(
        type = R.string.snowfall_heavy
    )
    data object SnowGrains: TypeWeather(
        type = R.string.snow_grains
    )
    data object RainShowersSlight: TypeWeather(
        type = R.string.rain_showers_slight
    )
    data object RainShowersModerate: TypeWeather(
        type = R.string.rain_showers_moderate
    )
    data object RainShowersViolent: TypeWeather(
        type = R.string.rain_showers_violent
    )
    data object SnowShowersSlight: TypeWeather(
        type = R.string.snow_showers_slight
    )
    data object SnowShowersHeavy: TypeWeather(
        type = R.string.snow_showers_heavy
    )
    data object Thunderstorm: TypeWeather(
        type = R.string.thunderstorm
    )
    data object ThunderstormSlightHail: TypeWeather(
        type = R.string.thunderstorm_slight_hail
    )
    data object ThunderstormHeavyHail: TypeWeather(
        type = R.string.thunderstorm_heavy_hail
    )
    data object Unknown: TypeWeather(
        type = R.string.unknown_type_weather
    )

    companion object {
        fun getTypeWeatherFromCode(weatherCode: Int): TypeWeather {
            return when (weatherCode) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloud
                3 -> Overcast
                45 -> Fog
                48 -> DepositingRimeFog
                51 -> DrizzleLight
                53 -> DrizzleModerate
                55 -> DrizzleDenseIntensity
                56 -> FreezingDrizzle
                57 -> FreezingDrizzleDenseIntensity
                61 -> RainSlight
                63 -> RainModerate
                65 -> RainHeavy
                66 -> FreezingDrizzle
                67 -> FreezingDrizzleDenseIntensity
                71 -> SnowfallSlight
                73 -> SnowfallModerate
                75 -> SnowfallHeavy
                77 -> SnowGrains
                80 -> RainShowersSlight
                81 -> RainShowersModerate
                82 -> RainShowersViolent
                85 -> SnowShowersSlight
                86 -> SnowShowersHeavy
                95 -> Thunderstorm
                96 -> ThunderstormSlightHail
                99 -> ThunderstormHeavyHail
                else -> Unknown
            }
        }
    }
}