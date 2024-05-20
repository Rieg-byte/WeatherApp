package com.rieg.weatherapp.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.rieg.weatherapp.R
import com.rieg.weatherapp.domain.models.CurrentWeather
import com.rieg.weatherapp.domain.models.DayWeather
import com.rieg.weatherapp.domain.models.TypeWeather
import com.rieg.weatherapp.presentation.components.ErrorPlaceholder
import com.rieg.weatherapp.presentation.components.LoadingPlaceholder
import com.rieg.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ),
        onPermissionsResult = { result ->
            if (result.values.all {it}) {
                mainViewModel.loadWeatherInfo()
            }
        }
    )
    LaunchedEffect(key1 = locationPermissions) {
        if (!locationPermissions.allPermissionsGranted) {
            locationPermissions.launchMultiplePermissionRequest()
        }
    }
    val mainUiState by mainViewModel.mainUiState.collectAsState()
    MainBody(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState),
        onClickRefresh = mainViewModel::onRefresh,
        mainUiState = mainUiState
    )
}

@Composable
fun MainBody(
    modifier: Modifier = Modifier,
    onClickRefresh: () -> Unit,
    mainUiState: MainUiState
) {
    when (mainUiState) {
        is MainUiState.Error -> ErrorPlaceholder(modifier = modifier, onClickRefresh = onClickRefresh )
        is MainUiState.Loading -> LoadingPlaceholder(modifier = modifier)
        is MainUiState.Success -> WeatherInfo(
            modifier = modifier,
            location = mainUiState.location,
            currentWeather = mainUiState.weatherInfo.currentWeather,
            weeklyWeather = mainUiState.weatherInfo.weeklyWeather
        )
    }
}

@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    location: String,
    currentWeather: CurrentWeather,
    weeklyWeather: List<DayWeather>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        CurrentWeatherCard(
            location = location,
            temperature = currentWeather.temperature,
            weatherState = stringResource(id = currentWeather.typeWeather.type)
        )
        WeeklyWeatherDisplay(
            title = stringResource(id = R.string.week_weather),
            weeklyWeather = weeklyWeather
        )
    }

}

@Composable
fun CurrentWeatherCard(
    modifier: Modifier = Modifier,
    location: String,
    temperature: Double,
    weatherState: String

) {
    Card(
        modifier = modifier
            .height(180.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = location,
                    fontSize = 20.sp
                )
                Text(
                    text = "$temperature °C",
                    fontSize = 52.sp
                )
                Text(
                    text = weatherState,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Composable
fun WeeklyWeatherDisplay(
    modifier: Modifier = Modifier,
    title: String,
    weeklyWeather: List<DayWeather>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        weeklyWeather.forEach { dayWeather ->
            DayWeatherCard(
                day = changeDateFormat(dayWeather.time),
                dayOfWeek = getDayOfWeek(dayWeather.time).uppercase(),
                temperatureMin = dayWeather.temperatureMin,
                temperatureMax = dayWeather.temperatureMax
            )
        }

    }
}

@Composable
fun DayWeatherCard(
    modifier: Modifier = Modifier,
    day: String,
    dayOfWeek: String,
    temperatureMin: Double,
    temperatureMax: Double
) {
    Card(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = day,
                    fontSize = 12.sp
                )
                Text(
                    text = dayOfWeek,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "$temperatureMin° / $temperatureMax°",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}


private fun changeDateFormat(time: String): String {
    val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatterOutput = DateTimeFormatter.ofPattern("dd.MM")
    val date = LocalDate.parse(time, formatterInput)
    return date.format(formatterOutput)
}

private fun getDayOfWeek(time: String): String {
    val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(time, formatterInput)
    return date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru"))
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoPreview() {
    WeatherAppTheme {
        WeatherInfo(
            location = "Санкт-Петербург",
            currentWeather = CurrentWeather(
                temperature = -52.2,
                typeWeather = TypeWeather.SnowfallSlight
            ),
            weeklyWeather = listOf(
                DayWeather(
                    time = "2064-01-10",
                    temperatureMin = -54.2,
                    temperatureMax = -39.2
                )
            )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DayWeatherCardPreview() {
    WeatherAppTheme {
        DayWeatherCard(
            day = "18.05",
            dayOfWeek = "СБ",
            temperatureMin = 6.2,
            temperatureMax = 20.1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherCardPreview() {
    WeatherAppTheme {
        CurrentWeatherCard(
            location = "Москва",
            temperature = 20.1,
            weatherState = "Переменная облачность")
    }
}