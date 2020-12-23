package br.com.adriane.ambar.weather.repositories

import br.com.adriane.ambar.weather.entities.Forecast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ForecastRepository : JpaRepository<Forecast, Int>{
    fun findByCityId(cityId: Int): List<Forecast>
}