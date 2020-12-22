package br.com.adriane.ambar.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class WeatherApplication

fun main(args: Array<String>) {
	runApplication<WeatherApplication>(*args)
}
