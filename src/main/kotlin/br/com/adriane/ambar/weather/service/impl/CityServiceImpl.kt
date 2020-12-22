package br.com.adriane.ambar.weather.service.impl

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.mapper.CityMapper
import br.com.adriane.ambar.weather.repositories.CityRepository
import br.com.adriane.ambar.weather.rest.feign.ForecastWeatherClient
import br.com.adriane.ambar.weather.service.CityService
import org.springframework.stereotype.Service

@Service
class CityServiceImpl(
        val cityRepository: CityRepository,
        val forecastWeatherClient: ForecastWeatherClient,
        val cityMapper: CityMapper,
) :CityService {

    override fun listAllCities() = cityRepository.findAll()

    override fun saveCity(cityRequestId: Int): City {
        val city = requestForecastWeatherFromCity(cityRequestId)
        return cityRepository.save(city);
    }

    private fun requestForecastWeatherFromCity(cityRequestId: Int): City {
        val cityResponse = forecastWeatherClient.forecast15DaysByCity(cityRequestId)
        return cityMapper.fromResponseToEntity(cityResponse)
    }

}

