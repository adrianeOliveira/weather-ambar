package br.com.adriane.ambar.weather.rest.entities.response.climateweather

data class ClimateWeatherResponse (val id: Int,
                                   val name: String,
                                   val state: String,
                                   val country: String,
                                   val data: List<ClimateWeatherData>)