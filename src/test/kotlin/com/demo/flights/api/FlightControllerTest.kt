package com.demo.flights.api;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Test class for the {@link com.demo.flights.api.FlightController}
 */
@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {

    }

    @Test
    @DisplayName("Test find by id")
    @Throws(Exception::class)
    fun findByIdTest() {
        val id = "6"

        mockMvc.perform(
            get("/api/flight/{id}", id)
                .with(
                    SecurityMockMvcRequestPostProcessors.user("flightsadmin")
                        .password("flightsadmin")
                )
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.airlineCode").value("BA"))
            .andDo(print())
    }
}
