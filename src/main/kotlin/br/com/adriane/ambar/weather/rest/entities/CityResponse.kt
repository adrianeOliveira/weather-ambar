package br.com.adriane.ambar.weather.rest.entities

data class CityResponse constructor(val id: Int,
                               val name: String,
                               val state: String,
                               val country: String,
                               val data: List<CityData>)