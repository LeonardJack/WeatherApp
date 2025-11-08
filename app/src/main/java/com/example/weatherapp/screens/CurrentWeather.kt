package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.models.Current
import androidx.compose.foundation.layout.*
import coil.compose.AsyncImage


@Composable
fun CurrentWeather(current: Current) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Weather Icon
        AsyncImage(
            model = "https:${current.condition.icon}",
            contentDescription = "current.condition.text",
            modifier = Modifier.size(64.dp)
        )
        // Temperature
        Text(
            text = "${current.temp_c}°C",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weather condition
        Text(
            text = current.condition.text,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Weather details
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherDetail("Feels like", "${current.feelslike_c}°C")
            WeatherDetail("Humidity", "${current.humidity}%")
            WeatherDetail("Wind", "${current.wind_kph} kph")
            WeatherDetail("Pressure", "${current.pressure_mb} mb")
            WeatherDetail("Visibility", "${current.vis_km} km")
        }
    }
}

@Composable
fun WeatherDetail(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
