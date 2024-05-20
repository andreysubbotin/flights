package com.demo.flights.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "airdatas_airport")
open class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Size(max = 31)
    @NotNull
    @Column(name = "code", nullable = false, length = 31)
    open var code: String? = null

    @Column(name = "lat")
    open var lat: Double? = null

    @Column(name = "lon")
    open var lon: Double? = null
}