package com.demo.flights.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import org.springframework.util.StringUtils.trimLeadingCharacter
import org.springframework.util.StringUtils.trimTrailingCharacter

@Configuration
class MvcConfiguration(val applicationProperties: ApplicationProperties) {

    @Bean
    fun mvcConfigurer() : WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addViewControllers(registry: ViewControllerRegistry) {
                val baseUrl: String? = applicationProperties.frontend.baseUrl

                if (!StringUtils.hasLength(baseUrl)) {
                    registry.addViewController("/").setViewName("forward:/index.html")
                } else {
                    val normalizedUrl: String = normalizeUrl(baseUrl!!)
                    registry.addViewController("/$normalizedUrl").setViewName("forward:/$normalizedUrl/index.html")
                }
            }

            fun normalizeUrl(url: String) : String {
                return trimTrailingCharacter(trimLeadingCharacter(url, '/'), '/')
            }
        }
    }
}
