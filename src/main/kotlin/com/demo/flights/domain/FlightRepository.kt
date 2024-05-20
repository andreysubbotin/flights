package com.demo.flights.domain;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.OffsetDateTime

interface FlightRepository : JpaRepository<Flight, Long> {

    @Query(
        """select f from Flight f
where f.fromAirport.id = ?1 and f.toAirport.id = ?2 and f.takeoffDate >= ?3 and f.takeoffDate <= ?4
order by f.takeoffDate"""
    )
    fun findByAirportsAndDates(
        fromId: Int, toId: Int, dateMin: OffsetDateTime, dateMax: OffsetDateTime
    ): List<Flight>
}