package br.com.adriane.ambar.weather.mapper

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.rest.entities.response.CityAvgPrecipitation
import br.com.adriane.ambar.weather.rest.entities.response.CityResponse
import br.com.adriane.ambar.weather.rest.entities.response.climateweather.ClimateWeatherResponse
import org.springframework.stereotype.Component

@Component
class CityMapper {

    fun fromResponseToEntity(climateWeatherResponse: ClimateWeatherResponse) = City(
                id = climateWeatherResponse.id,
                name = climateWeatherResponse.name,
                state = climateWeatherResponse.state,
                country = climateWeatherResponse.country
        )

    fun fromEntityToResponse(city: City) = CityResponse(
            id = city.id,
            name = city.name,
            state = city.state,
            country = city.country
    )

    fun cityAvgPrecipitationToResponse(cityAvgPrecipitation: br.com.adriane.ambar.weather.entities.CityAvgPrecipitation) =
            CityAvgPrecipitation(
                    id = cityAvgPrecipitation.id,
                    name = cityAvgPrecipitation.name,
                    state = cityAvgPrecipitation.state,
                    country = cityAvgPrecipitation.country,
                    avgPrecipitation = cityAvgPrecipitation.avgPrecipitation
            )
}