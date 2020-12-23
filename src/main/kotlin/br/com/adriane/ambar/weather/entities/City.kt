package br.com.adriane.ambar.weather.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class City(
    @Id @Column(name = "id_city")
    val id: Int,
    val name: String,
    val state: String,
    val country: String
)

data class CityAvgPrecipitation(
        val id: Int,
        val name: String,
        val state: String,
        val country: String,
        val avgPrecipitation: Double
)