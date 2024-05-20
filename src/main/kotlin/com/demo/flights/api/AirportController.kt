package com.demo.flights.api

import com.demo.flights.domain.Airport
import com.demo.flights.domain.AirportRepository
import com.demo.flights.dto.AirportDto
import com.demo.flights.dto.AirportMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/airport")
class AirportController(private val airportRepository: AirportRepository, private val airportMapper: AirportMapper) {
    @GetMapping
    fun getList(): List<AirportDto> {
        val airports: List<Airport> = airportRepository.findByOrderByNameAsc()
        val airportDtos: List<AirportDto> = airports.map(airportMapper::toDto)
        return airportDtos
    }

}

