package br.com.adriane.ambar.weather.service

import br.com.adriane.ambar.weather.entities.ForecastWeather

interface ForestWeahterService {

    fun listAllForecastByCity(cityId: Int)
    fun saveForecastWeather(forecastWeather: ForecastWeather): ForecastWeather
}