package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.models.Forecast
import androidx.compose.material3.Button


@Composable
fun DailyForecast(forecasts: List<Forecast>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "5-Day Forecast",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forecasts) { forecast ->
                ForecastItem(forecast = forecast)
            }
        }
    }
}
@Composable
fun ForecastItem(forecast: Forecast) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Date
            Text(
                text = forecast.date,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Weather Image and Condition
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_wb_cloudy_24),
                        contentDescription = "Weather Condition",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = forecast.condition,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Temperature
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "H: ${forecast.temperatureHigh}°C",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "L: ${forecast.temperatureLow}°C",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Additional Details
            Column {
                Text(
                    text = "Precipitation: ${forecast.precipitationType}, ${forecast.precipitationAmount}mm (${forecast.precipitationProbability}%)",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Wind: ${forecast.windDirection} ${forecast.windSpeed} km/h",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Humidity: ${forecast.humidity}%",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}