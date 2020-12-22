package br.com.adriane.ambar.weather.repositories

import br.com.adriane.ambar.weather.entities.City
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : JpaRepository<City, Integer>
