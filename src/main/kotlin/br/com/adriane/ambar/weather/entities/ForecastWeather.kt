package br.com.adriane.ambar.weather.entities

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class ForecastWeather(
    @Id @Column(name = "id_forecast_weather")
    val id: Int? = null,
    val date: LocalDate,
    val rainProbability: Int,
    val rainPrecipitation: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    @ManyToOne @JoinColumn(name = "city_id_city")
    val city: City? = null
)