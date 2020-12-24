package br.com.adriane.ambar.weather.rest.entities.response

data class AnalysisResponse(
        val city: CityResponse,
        val avgPrecipitationResponseList: List<CityAvgPrecipitationResponse>
)

data class CityAvgPrecipitationResponse(
        val id: Int,
        val name: String,
        val state: String,
        val country: String,
        val avgPrecipitation: Double
)

data class CityResponse(
        val id: Int,
        val name: String,
        val state: String,
        val country: String
)