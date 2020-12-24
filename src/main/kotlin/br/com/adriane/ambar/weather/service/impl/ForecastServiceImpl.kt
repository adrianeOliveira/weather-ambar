package br.com.adriane.ambar.weather.service.impl

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.Forecast
import br.com.adriane.ambar.weather.mapper.CityMapper
import br.com.adriane.ambar.weather.mapper.ForecastMapper
import br.com.adriane.ambar.weather.repositories.ForecastRepository
import br.com.adriane.ambar.weather.rest.entities.request.CityRequest
import br.com.adriane.ambar.weather.rest.feign.ClimateWeatherClient
import br.com.adriane.ambar.weather.service.ForecastService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ForecastServiceImpl(
        val climateWeatherClient: ClimateWeatherClient,
        val cityMapper: CityMapper,
        val forecastMapper: ForecastMapper,
        val forecastRepository: ForecastRepository
): ForecastService {

    private val log = LoggerFactory.getLogger(ForecastServiceImpl::class.java)

    override fun buildForecastFromRequest(cityRequest: CityRequest): Pair<City, List<Forecast>> {
        log.info("M=buildForecastFromRequest, I= buscando cidade e previsão, cityRequest = $cityRequest")
        val response = climateWeatherClient.forecast15DaysByCity(cityRequest.cityId)
        val city = cityMapper.fromResponseToEntity(response)

        val forecastList = response.data
                .map(forecastMapper::fromResponseToEntity)
        log.info("M=buildForecastFromRequest, I=consulta finalizada, city = $city")
        return Pair(city, forecastList)
    }

    @Transactional
    override fun insertForecast(forecast: Forecast): Forecast {
        log.info("M=insertForecast, forecast = $forecast")
        if (forecast.id == null) {
            return forecastRepository.save(forecast)
        }
        throw IllegalArgumentException("Campo id para previsão deve ser vazio na operação de inserção")
    }

    @Transactional(readOnly = true)
    override fun listAllForecastByCity(cityId: Int) =
            forecastRepository.findByCityId(cityId)

}