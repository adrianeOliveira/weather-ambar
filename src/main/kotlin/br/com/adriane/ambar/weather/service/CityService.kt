package br.com.adriane.ambar.weather.service

import br.com.adriane.ambar.weather.entities.City

interface CityService {
    fun listAllCities(): List<City>

    fun insertCity(city: City): City
}