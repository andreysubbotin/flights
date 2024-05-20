package com.demo.flights.config

import com.amplicode.core.auth.AuthenticationInfoProvider
import com.amplicode.core.auth.UserDetailsAuthenticationInfoProvider
import com.amplicode.core.security.Authorities
import com.amplicode.core.security.UnauthorizedStatusAuthenticationEntryPoint
import com.amplicode.core.security.formlogin.FormLoginAuthenticationFailureHandler
import com.amplicode.core.security.formlogin.FormLoginAuthenticationSuccessHandler
import com.amplicode.core.security.formlogin.FormLoginLogoutSuccessHandler
import jakarta.servlet.DispatcherType
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfiguration(
    @Value("\${app.security.in-memory.admin.username}")
    val adminUsername: String,
    @Value("\${app.security.in-memory.admin.password}")
    val adminPassword: String
) {

    @Bean
    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return FormLoginAuthenticationSuccessHandler()
    }

    @Bean
    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return FormLoginAuthenticationFailureHandler()
    }

    @Bean
    fun logoutSuccessHandler(): LogoutSuccessHandler {
        return FormLoginLogoutSuccessHandler()
    }

    @Bean
    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return UnauthorizedStatusAuthenticationEntryPoint()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        //Form Login
        http.formLogin { formLogin ->
            formLogin.successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .permitAll()
        }
        //Exception handling
        http.exceptionHandling { exceptionHandling ->
            exceptionHandling.authenticationEntryPoint(authenticationEntryPoint())
        }
        //Logout
        http.logout { logout ->
            logout.logoutSuccessHandler(logoutSuccessHandler())
        }
        //Authorize
        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/graphql/**").permitAll()
                .requestMatchers("/graphql").permitAll()
                .requestMatchers("/api/addon/**").authenticated()
                .requestMatchers("/", "/index.html", "/assets/**").permitAll()
                .anyRequest().authenticated()
        }
        //CORS
        http.cors(Customizer.withDefaults())
        //CSRF
        http.csrf { csrf -> csrf.disable() }

        http.httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val admin: UserDetails = User.builder()
            .username(adminUsername)
            .password(adminPassword)
            .authorities("ROLE_ADMIN", Authorities.FULL_ACCESS)
            .build()

        return InMemoryUserDetailsManager(admin)
    }

    @Bean
    fun authenticationInfoProvider(): AuthenticationInfoProvider {
        return UserDetailsAuthenticationInfoProvider()
    }
}
