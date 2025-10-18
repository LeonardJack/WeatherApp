package com.example.weatherapp.models

data class Weather(
    val current: Current,
    val forecast: List<Forecast>
)

data class Current(
    val condition: String,
    val temperature: Double,
    val precipitationType: String,
    val precipitationAmount: Double,
    val windDirection: String,
    val windSpeed: Double,
    val humidity:Int,
    val weatherImage: String? = null
)

data class Forecast(
    val date: String,
    val condition: String,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    val precipitationType: String,
    val precipitationAmount: Double,
    val precipitationProbability: Int,
    val windDirection: String,
    val windSpeed: Double,
    val humidity:Int,
    val weatherImage: String? = null
)