package br.com.adriane.ambar.weather

import br.com.adriane.ambar.weather.mapper.CityMapper
import br.com.adriane.ambar.weather.mapper.ForecastMapper
import br.com.adriane.ambar.weather.repositories.ForecastRepository
import br.com.adriane.ambar.weather.rest.entities.request.CityRequest
import br.com.adriane.ambar.weather.rest.entities.response.climateweather.ClimateWeatherData
import br.com.adriane.ambar.weather.rest.entities.response.climateweather.ClimateWeatherResponse
import br.com.adriane.ambar.weather.rest.entities.response.climateweather.RainResponse
import br.com.adriane.ambar.weather.rest.entities.response.climateweather.TemperatureResponse
import br.com.adriane.ambar.weather.rest.feign.ClimateWeatherClient
import br.com.adriane.ambar.weather.service.impl.ForecastServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class BuildForecastFromRequest {

	@InjectMocks
	val cityMapper = CityMapper()

	@InjectMocks
	val forecastMapper =  ForecastMapper()

	val climateWeatherClient =
			Mockito.mock(ClimateWeatherClient::class.java)

	@InjectMocks
	val forecastService =  ForecastServiceImpl(
			climateWeatherClient,
			cityMapper,
			forecastMapper,
			Mockito.mock(ForecastRepository::class.java)
	)

	@Test
	fun `build city and forecast with success`() {
		val climateWeatherResponse = buildClimateWeatherResponseObj()
		Mockito.`when`(climateWeatherClient.forecast15DaysByCity(Mockito.anyInt()))
				.thenReturn(climateWeatherResponse)

		val pair = forecastService.buildForecastFromRequest(CityRequest(4318))

		val cityResult = pair.first
		Assertions.assertEquals(climateWeatherResponse.id, cityResult.id)
		Assertions.assertEquals(climateWeatherResponse.name, cityResult.name)
		Assertions.assertEquals(climateWeatherResponse.state, cityResult.state)
		Assertions.assertEquals(climateWeatherResponse.country, cityResult.country)

		Assertions.assertEquals(climateWeatherResponse.data.size, pair.second.size)

	}

	private fun buildClimateWeatherResponseObj() =
		ClimateWeatherResponse(4318,
				"Santa Fé de Goiás",
				"GO", "BR  ",
				buildClimateWeatherDataList()
		)

	private fun buildDateFromParameters(day: Int, month: Int, year: Int) =
			LocalDate.now()
					.withDayOfMonth(day)
					.withMonth(month)
					.withYear(year)

	private fun buildClimateWeatherDataList() =
		Arrays.asList(
			ClimateWeatherData(
					buildDateFromParameters(23, 12, 2020),
					RainResponse(90, 8),
					TemperatureResponse(22, 27)
			),
			ClimateWeatherData(
					buildDateFromParameters(24, 12, 2020),
					RainResponse(67, 12),
					TemperatureResponse(23, 27)
			),
			ClimateWeatherData(
					buildDateFromParameters(25, 12, 2020),
					RainResponse(90, 16),
					TemperatureResponse(24, 28)
			),
			ClimateWeatherData(
					buildDateFromParameters(26, 12, 2020),
					RainResponse(90, 16),
					TemperatureResponse(22, 28)
			),
			ClimateWeatherData(
					buildDateFromParameters(27, 12, 2020),
					RainResponse(90, 23),
					TemperatureResponse(22, 27)
			),
			ClimateWeatherData(
					buildDateFromParameters(28, 12, 2020),
					RainResponse(80, 10),
					TemperatureResponse(21, 29)
			),
			ClimateWeatherData(
					buildDateFromParameters(29, 12, 2020),
					RainResponse(0, 1),
					TemperatureResponse(23, 33)
			)
		)

}
