package com.demo.flights

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class FlightsApplication

fun main(args: Array<String>) {
    runApplication<FlightsApplication>(*args)
}
