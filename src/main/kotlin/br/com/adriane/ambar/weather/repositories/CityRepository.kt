package br.com.adriane.ambar.weather.repositories

import br.com.adriane.ambar.weather.entities.City
import br.com.adriane.ambar.weather.entities.CityAvgPrecipitation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CityRepository : JpaRepository<City, Int> {
    @Query(nativeQuery = true,
            value = "select c.* from city c where c.id_city = (" +
                    "select TOP 1 f.city_id_city from forecast f " +
                    "where f.date_forecast between :initialDate and :finalDate " +
                    "group by f.city_id_city, f.max_temperature " +
                    "having  MAX(f.max_temperature) " +
                    "order by f.max_temperature desc" +
                    ")")
    fun findCityWithMaxTemperature(@Param("initialDate") initialDate: LocalDate,
                                   @Param("finalDate") finalDate: LocalDate): City

    @Query(value = "select new br.com.adriane.ambar.weather.entities.CityAvgPrecipitation(" +
            " c.id, c.name, c.state, c.country, avg(f.rainPrecipitation))" +
            "from City c " +
            "inner join Forecast f on f.city.id = c.id " +
            "where f.date between :initialDate and :finalDate " +
            "group by c.id")
    fun findCitiesWithAvgPrecipitation(@Param("initialDate") initialDate: LocalDate,
                                       @Param("finalDate") finalDate: LocalDate): List<CityAvgPrecipitation>
}
