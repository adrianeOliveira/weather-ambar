package br.com.adriane.ambar.weather.controller

import br.com.adriane.ambar.weather.repositories.CityRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cidades")
class CityController(
        val cityRepository: CityRepository
) {
    @GetMapping
    fun listCities() = cityRepository.findAll()
}