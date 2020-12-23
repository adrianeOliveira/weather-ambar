package br.com.adriane.ambar.weather.rest.entities.response.climateweather

import java.time.LocalDate

data class ClimateWeatherData(val date: LocalDate,
                              val rain: RainResponse,
                              val temperature: TemperatureResponse
)