package br.com.adriane.ambar.weather.mapper

import br.com.adriane.ambar.weather.entities.Forecast
import br.com.adriane.ambar.weather.rest.entities.response.ClimateWeatherData
import org.springframework.stereotype.Component

@Component
class ForecastMapper {

    fun fromResponseToEntity(data: ClimateWeatherData) =
        Forecast(date = data.date,
        rainProbability = data.rain.probability,
        rainPrecipitation = data.rain.precipitation,
        minTemperature = data.temperature.min,
        maxTemperature = data.temperature.max)
}