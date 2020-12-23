package br.com.adriane.ambar.weather.rest.controller

import br.com.adriane.ambar.weather.rest.entities.request.CityRequest
import br.com.adriane.ambar.weather.service.CityService
import br.com.adriane.ambar.weather.service.ForecastService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cidades")
class CityController(
        val cityService: CityService,
        val forecastService: ForecastService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listCities() = cityService.listAllCities()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insertCity(@RequestBody cityRequest: CityRequest) {
        val pair = forecastService.buildForecastFromRequest(cityRequest)
        cityService.insertCity(pair.first)
        pair.second.forEach() {
            it.city = pair.first
            forecastService.insertForecast(it)
        }
    }

    @RequestMapping("/{cityId}/forecast")
    @ResponseStatus(HttpStatus.OK)
    fun listForecastByCity(@PathVariable cityId: Int)
     = forecastService.listAllForecastByCity(cityId)
}