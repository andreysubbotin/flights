package com.demo.flights.dto

import com.demo.flights.domain.Airport
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class AirportMapper {

    abstract fun toEntity(airportDto: AirportDto): Airport

    abstract fun toDto(airport: Airport): AirportDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(airportDto: AirportDto, @MappingTarget airport: Airport): Airport
}