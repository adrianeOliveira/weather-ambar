package br.com.adriane.ambar.weather.mapper

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.rest.entities.CityResponse
import org.springframework.stereotype.Component

@Component
class CityMapper {

    fun fromResponseToEntity(cityResponse: CityResponse) = City(
                id = cityResponse.id,
                name = cityResponse.name,
                state = cityResponse.state,
                country = cityResponse.country
        )
}