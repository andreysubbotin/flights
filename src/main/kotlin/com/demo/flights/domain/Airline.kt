package com.demo.flights.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.JavaType
import org.hibernate.type.descriptor.java.BooleanJavaType

@Entity
@Table(name = "airdatas_airline")
open class Airline {
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

    @Size(max = 255)
    @Column(name = "country")
    open var country: String? = null

    @JavaType(BooleanJavaType::class)
    @Column(name = "archived")
    open var archived: Boolean? = null
}