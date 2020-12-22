package br.com.adriane.ambar.weather.rest.controller

import br.com.adriane.ambar.weather.service.CityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cidades")
class CityController(
        val cityService: CityService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listCities() = cityService.listAllCities()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun consultAndUpdateCity(@RequestParam("id")cityId: Int) {
        cityService.saveCity(cityId)
    }
}