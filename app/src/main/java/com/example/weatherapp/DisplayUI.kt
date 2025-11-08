package com.example.weatherapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.WeatherState
import com.example.weatherapp.screens.CurrentWeather
import com.example.weatherapp.screens.DailyForecast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    val weatherState by mainViewModel.weatherState.collectAsState()

    val items = listOf(Screen.CurrentWeather, Screen.DailyForecast)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (weatherState) {
                        is WeatherState.Success -> {
                            val location = (weatherState as WeatherState.Success).data.location
                            Text("${location.name}, ${location.country}")
                        }
                        is WeatherState.Loading -> {
                            Text("Loading weather...")
                        }
                        else -> {
                            Text("Weather App")
                        }
                    }
                },
                actions = {
                    // Refresh button
                    IconButton(
                        onClick = { mainViewModel.refreshWeather() },
                        enabled = weatherState !is WeatherState.Loading
                    ) {
                        if (weatherState is WeatherState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Refresh weather"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (weatherState) { //HERE
            is WeatherState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircularProgressIndicator()
                        Text("Loading weather data...")
                    }
                }
            }
            is WeatherState.Error -> {
                val errorState = weatherState as WeatherState.Error
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Error loading weather data",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(errorState.message)
                        Button(onClick = { mainViewModel.refreshWeather() }) {
                            Text("Retry")
                        }
                    }
                }
            }
            is WeatherState.Success -> {
                val weatherResponse = (weatherState as WeatherState.Success).data

                NavHost(
                    navController = navController,
                    startDestination = Screen.CurrentWeather.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.CurrentWeather.route) {
                        CurrentWeather(current = weatherResponse.current)
                    }
                    composable(Screen.DailyForecast.route) {
                        DailyForecast(forecasts = weatherResponse.forecast.forecastday)
                    }
                }
            }

            else -> {}
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object CurrentWeather : Screen("current_weather", "Current", Icons.Default.Home)
    object DailyForecast : Screen("daily_forecast", "Forecast", Icons.Default.DateRange)
}