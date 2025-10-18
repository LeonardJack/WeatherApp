package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Weather

class MainViewModel : ViewModel() {
    var weather: Weather? = null
        private set

    init {
        initializePlaceholderData()
    }

    private fun initializePlaceholderData() {
        val current = Current(
            condition = "Sunny",
            temperature = 22.5,
            precipitationType = "Rain",
            precipitationAmount = 0.0,
            windDirection = "NE",
            windSpeed = 15.0,
            humidity = 65
        )

        val forecasts = listOf(
            Forecast(
                date = "Monday, Oct 2",
                condition = "Partly Cloudy",
                temperatureHigh = 24.0,
                temperatureLow = 16.0,
                precipitationType = "Rain",
                precipitationAmount = 2.5,
                precipitationProbability = 30,
                windDirection = "NE",
                windSpeed = 12.0,
                humidity = 70
            ),
            Forecast(
                date = "Tuesday, Oct 3",
                condition = "Sunny",
                temperatureHigh = 26.0,
                temperatureLow = 18.0,
                precipitationType = "None",
                precipitationAmount = 0.0,
                precipitationProbability = 0,
                windDirection = "SW",
                windSpeed = 8.0,
                humidity = 60
            ),
            Forecast(
                date = "Wednesday, Oct 4",
                condition = "Cloudy",
                temperatureHigh = 20.0,
                temperatureLow = 14.0,
                precipitationType = "Rain",
                precipitationAmount = 5.2,
                precipitationProbability = 80,
                windDirection = "E",
                windSpeed = 20.0,
                humidity = 85
            ),
            Forecast(
                date = "Thursday, Oct 5",
                condition = "Clear",
                temperatureHigh = 23.0,
                temperatureLow = 15.0,
                precipitationType = "None",
                precipitationAmount = 0.0,
                precipitationProbability = 0,
                windDirection = "NW",
                windSpeed = 10.0,
                humidity = 55
            ),
            Forecast(
                date = "Friday, Oct 6",
                condition = "Thunderstorms",
                temperatureHigh = 19.0,
                temperatureLow = 13.0,
                precipitationType = "Rain",
                precipitationAmount = 15.8,
                precipitationProbability = 90,
                windDirection = "SE",
                windSpeed = 25.0,
                humidity = 90
            )
        )

        weather = Weather(current = current, forecast = forecasts)
    }
}