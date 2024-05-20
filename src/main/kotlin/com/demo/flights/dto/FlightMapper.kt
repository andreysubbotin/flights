package com.demo.flights.dto

import com.demo.flights.domain.Flight
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [AirportMapper::class, AirportMapper::class]
)
abstract class FlightMapper {

    @Mappings(
        Mapping(source = "airlineName", target = "airline.name"),
        Mapping(source = "airlineCode", target = "airline.code")
    )
    abstract fun toEntity(flightDto: FlightDto): Flight

    @InheritInverseConfiguration(name = "toEntity")
    abstract fun toDto(flight: Flight): FlightDto

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(flightDto: FlightDto, @MappingTarget flight: Flight): Flight
}