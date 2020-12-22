package br.com.adriane.ambar.weather.rest.entities

import java.time.LocalDate

data class CityData(val date: LocalDate,
               val rain: RainResponse,
               val temperature: TemperatureResponse
)