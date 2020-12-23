package br.com.adriane.ambar.weather.rest.entities.response

data class AnalysisResponse(
        val city: CityResponse,
        val avgPrecipitationList: List<CityAvgPrecipitation>
)