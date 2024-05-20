package com.demo.flights.api

import com.demo.flights.domain.Flight
import com.demo.flights.domain.FlightRepository
import com.demo.flights.dto.FlightDto
import com.demo.flights.dto.FlightMapper
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RestController
@RequestMapping("/api/flight")
class FlightController(private val flightRepository: FlightRepository, private val flightMapper: FlightMapper) {
    @GetMapping
    fun findByAirportsAndDates(
        @RequestParam from: Int,
        @RequestParam to: Int,
        @RequestParam dateMin: LocalDate,
        @RequestParam dateMax: LocalDate
    ): List<FlightDto> {
        val start = dateMin.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
        val end = dateMax.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toOffsetDateTime()

        val flights: List<Flight> =
            flightRepository.findByAirportsAndDates(from, to, start, end)
        val flightDtos: List<FlightDto> = flights.map(flightMapper::toDto)
        return flightDtos
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<FlightDto> {
        val flightOptional: Optional<Flight> = flightRepository.findById(id)
        val flightDto: FlightDto = flightMapper.toDto(flightOptional.orElse(null));
        return Optional.ofNullable(flightDto)
    }
}

