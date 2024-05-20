package com.demo.flights.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * DTO for {@link com.demo.flights.domain.Airport}
 */
data class AirportDto(
    var id: Int? = null,
    @field:NotNull @field:Size(max = 255) var name: String? = null,
    @field:NotNull @field:Size(max = 31) var code: String? = null
) : Serializable