package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.services.LocationService

class MainViewModel (
    private val weatherRepository: WeatherRepository,
    private val locationService: LocationService
) : ViewModel() {

    fun refreshWeather() {
        loadWeatherWithLocation()
    }

    private val _weatherState = MutableStateFlow<WeatherState?>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState?> = _weatherState.asStateFlow()

    val weather: WeatherResponse?
        get() = when (val state = weatherState.value) {
            is WeatherState.Success -> state.data
            else -> null
        }

    init {
        loadWeatherWithLocation()
    }

    private fun loadWeatherWithLocation() {
        viewModelScope.launch {
            if (locationService.hasLocationPermission()) {
                try {
                    val location = locationService.getCurrentLocation()
                    if (location != null) {
                        val locationQuery = "${location.latitude},${location.longitude}"

                        loadWeatherData(locationQuery)
                    } else{
                        loadWeatherData("Halifax")
                    }
                } catch (e: Exception) {
                    loadWeatherData("Halifax")

                    }
                }
            }
        }
    fun loadWeatherData(locationQuery: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val weatherResponse = weatherRepository.getWeather(locationQuery)
                _weatherState.value = WeatherState.Success(weatherResponse)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Failed to Load")

            }
        }
    }
    }


    sealed class WeatherState {
        object Loading : WeatherState()
        data class Success(val data: WeatherResponse) : WeatherState()
        data class Error(val message: String) : WeatherState()
    }






