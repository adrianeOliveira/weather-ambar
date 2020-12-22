package br.com.adriane.ambar.weather.rest.feign

import br.com.adriane.ambar.weather.rest.entities.CityResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name="weather", url = "\${weather.forecast.url}")
interface ForecastWeatherClient {

    @GetMapping("{cityId}/days/15?token=\${weather.forecast.token}")
    fun forecast15DaysByCity(@PathVariable cityId: Int): CityResponse
}