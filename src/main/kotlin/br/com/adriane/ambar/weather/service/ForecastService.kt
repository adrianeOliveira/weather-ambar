package br.com.adriane.ambar.weather.service

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.Forecast
import br.com.adriane.ambar.weather.rest.entities.request.CityRequest

interface ForecastService {

    fun buildForecastFromRequest(cityRequest: CityRequest): Pair<City, List<Forecast>>

    fun insertForecast(forecast: Forecast): Forecast

    fun listAllForecastByCity(cityId: Int): List<Forecast>

}
