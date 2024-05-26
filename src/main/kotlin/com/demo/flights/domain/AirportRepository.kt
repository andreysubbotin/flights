package com.demo.flights.domain;

import org.springframework.data.jpa.repository.JpaRepository

interface AirportRepository : JpaRepository<Airport, Int> {

    fun findByOrderByNameAsc(): List<Airport>
}