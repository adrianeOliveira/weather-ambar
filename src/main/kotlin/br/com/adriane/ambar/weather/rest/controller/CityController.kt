package br.com.adriane.ambar.weather.rest.controller

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.Forecast
import br.com.adriane.ambar.weather.mapper.CityMapper
import br.com.adriane.ambar.weather.rest.entities.request.CityRequest
import br.com.adriane.ambar.weather.rest.entities.response.AnalysisResponse
import br.com.adriane.ambar.weather.service.CityService
import br.com.adriane.ambar.weather.service.ForecastService
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/cidades")
class CityController(
        val cityService: CityService,
        val forecastService: ForecastService,
        val cityMapper: CityMapper
) {

    private val log = LoggerFactory.getLogger(CityController::class.java)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listCities(): List<City> {
        log.info("M=listCities, I= listando cidades consultadas")
        return cityService.listAllCities()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insertCity(@RequestBody cityRequest: CityRequest) {
        log.info("M=insertCity, cityRequest = $cityRequest")
        val pair = forecastService.buildForecastFromRequest(cityRequest)

        cityService.insertCity(pair.first)

        pair.second.forEach() {
            it.city = pair.first
            forecastService.insertForecast(it)
        }
        log.info("M=insertCity, I= busca finalizada com sucesso")
    }

    @GetMapping("/{cityId}/previsao")
    @ResponseStatus(HttpStatus.OK)
    fun listForecastByCity(@PathVariable cityId: Int): List<Forecast> {
        log.info("M=listForecastByCity, cityId = $cityId")
        return forecastService.listAllForecastByCity(cityId)
    }

    @GetMapping("/analise")
    @ResponseStatus(HttpStatus.OK)
    fun analysisForecastByTime(@RequestParam("dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") initialDate: LocalDate,
                               @RequestParam("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") finalDate: LocalDate): AnalysisResponse {
        log.info("M=analysisForecastByTime, data inicial = $initialDate, data final = $finalDate")
        val city = cityService.findCityWithMaxTemperatureByTime(initialDate, finalDate)

        val avgPrecipitationList = cityService
                .findAvgPrecipitationPerCity(initialDate, finalDate)
                .map(cityMapper::cityAvgPrecipitationToResponse)

        return AnalysisResponse(city = cityMapper.fromEntityToResponse(city),
                avgPrecipitationResponseList = avgPrecipitationList)
    }
}