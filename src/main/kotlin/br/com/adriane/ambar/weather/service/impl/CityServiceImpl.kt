package br.com.adriane.ambar.weather.service.impl

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.repositories.CityRepository
import br.com.adriane.ambar.weather.service.CityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityServiceImpl(
        val cityRepository: CityRepository
): CityService {

    @Transactional(readOnly = true)
    override fun listAllCities() = cityRepository.findAll()

    override fun insertCity(city: City): City {
        if (cityRepository.existsById(city.id)) {
            throw IllegalArgumentException("Não é possível adicionar uma cidade que já existe.")
        }
       return cityRepository.save(city)
    }

}

