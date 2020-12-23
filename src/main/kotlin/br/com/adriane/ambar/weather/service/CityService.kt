package br.com.adriane.ambar.weather.service

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.CityAvgPrecipitation
import java.time.LocalDate

interface CityService {
    fun listAllCities(): List<City>

    fun insertCity(city: City): City

    fun findCityWithMaxTemperatureByTime(initialDate: LocalDate, finalDate: LocalDate): City

    fun findAvgPrecipitationPerCity(initialDate: LocalDate, finalDate: LocalDate): List<CityAvgPrecipitation>
}