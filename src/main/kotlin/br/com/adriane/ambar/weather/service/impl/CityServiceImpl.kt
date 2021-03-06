package br.com.adriane.ambar.weather.service.impl

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.CityAvgPrecipitation
import br.com.adriane.ambar.weather.repositories.CityRepository
import br.com.adriane.ambar.weather.service.CityService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class CityServiceImpl(
        val cityRepository: CityRepository
): CityService {

    private val log = LoggerFactory.getLogger(CityServiceImpl::class.java)

    @Transactional(readOnly = true)
    override fun listAllCities() = cityRepository.findAll()

    override fun insertCity(city: City): City {
        log.info("M=insertCity, city = $city")
        if (cityRepository.existsById(city.id)) {
            throw IllegalArgumentException("Não é possível adicionar uma cidade que já existe.")
        }
       return cityRepository.save(city)
    }

    override fun findCityWithMaxTemperatureByTime(initialDate: LocalDate, finalDate: LocalDate): City {
        log.info("M=findCityWithMaxTemperatureByTime, initialDate = $initialDate, finalDate = $finalDate")
        return cityRepository.findCityWithMaxTemperature(initialDate, finalDate)
    }

    override fun findAvgPrecipitationPerCity(initialDate: LocalDate, finalDate: LocalDate): List<CityAvgPrecipitation> {
        log.info("M=findAvgPrecipitationPerCity, initialDate = $initialDate, finalDate = $finalDate")
        return cityRepository.findCitiesWithAvgPrecipitation(initialDate, finalDate)
    }

}

