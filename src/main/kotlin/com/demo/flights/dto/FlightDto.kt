package com.demo.flights.dto

import com.demo.flights.dto.AirportDto
import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.time.OffsetDateTime

/**
 * DTO for {@link com.demo.flights.domain.Flight}
 */
data class FlightDto(
    var id: Long? = null,
    @field:NotNull var number: Int? = null,
    var airlineName: String? = null,
    var airlineCode: String? = null,
    @field:NotNull var fromAirport: AirportDto? = null,
    @field:NotNull var toAirport: AirportDto? = null,
    @field:NotNull var takeoffDate: OffsetDateTime? = null,
    @field:NotNull var landingDate: OffsetDateTime? = null
) : Serializable