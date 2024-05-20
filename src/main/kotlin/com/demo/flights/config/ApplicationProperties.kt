package com.demo.flights.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
class ApplicationProperties(val frontend: FrontendProperties = FrontendProperties("")) {

    class FrontendProperties(val baseUrl: String? = null)
}
