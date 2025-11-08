package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.network.NetworkModule

class WeatherRepository {
    private val apiService = NetworkModule.weatherApiService
    private val apiKey = "98421e8fd58f4e8c94801651250611"

    suspend fun getWeather(
        location: String,
        days: Int = 7
    ): WeatherResponse{
        return apiService.getWeather(
            apiKey = apiKey,
            location = location,
            days = days
        )
    }

}