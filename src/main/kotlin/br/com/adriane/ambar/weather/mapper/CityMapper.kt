package br.com.adriane.ambar.weather.mapper

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.rest.entities.response.ClimateWeatherResponse
import org.springframework.stereotype.Component

@Component
class CityMapper {

    fun fromResponseToEntity(climateWeatherResponse: ClimateWeatherResponse) = City(
                id = climateWeatherResponse.id,
                name = climateWeatherResponse.name,
                state = climateWeatherResponse.state,
                country = climateWeatherResponse.country
        )
}