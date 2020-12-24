package br.com.adriane.ambar.weather.rest.entities.response.climateweather

import java.time.LocalDate

data class ClimateWeatherData(val date: LocalDate,
                              val rain: RainResponse,
                              val temperature: TemperatureResponse
)

data class ClimateWeatherResponse (val id: Int,
                                   val name: String,
                                   val state: String,
                                   val country: String,
                                   val data: List<ClimateWeatherData>)

data class RainResponse(val probability: Int,
                        val precipitation: Int)

data class TemperatureResponse(val min: Int,
                               val max: Int)