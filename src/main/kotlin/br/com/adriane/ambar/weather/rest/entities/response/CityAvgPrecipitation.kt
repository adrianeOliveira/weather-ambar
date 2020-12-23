package br.com.adriane.ambar.weather.rest.entities.response

data class CityAvgPrecipitation(
    val id: Int,
    val name: String,
    val state: String,
    val country: String,
    val avgPrecipitation: Double
)