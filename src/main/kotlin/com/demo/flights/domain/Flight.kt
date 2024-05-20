package com.demo.flights.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

@Entity
@Table(name = "airdatas_flight")
open class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @NotNull
    @Column(name = "number", nullable = false)
    open var number: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    open var airline: Airline? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_airport_id", nullable = false)
    open var fromAirport: Airport? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_airport_id", nullable = false)
    open var toAirport: Airport? = null

    @NotNull
    @Column(name = "takeoff_date", nullable = false)
    open var takeoffDate: OffsetDateTime? = null

    @NotNull
    @Column(name = "landing_date", nullable = false)
    open var landingDate: OffsetDateTime? = null
}