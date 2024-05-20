package com.demo.flights.controller

import com.amplicode.core.auth.AuthenticationInfoProvider
import jakarta.validation.constraints.NotNull
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import java.security.Principal

/**
 * Provides GraphQL queries that are required for Amplicode application UI based on React Admin.
 */
@Controller
class UserInfoController(val authenticationInfoProvider: AuthenticationInfoProvider) {

    /**
     * Checks whether a user is authenticated or not. If not, then [AccessDeniedException] is thrown.
     */
    @PreAuthorize("isAuthenticated()")
    @QueryMapping(name = "checkAuthenticated")
    fun checkAuthenticated() {
        //do nothing if user is authenticated
    }

    /**
     * Provides information about an authenticated user. If there is no authenticated user, then [AccessDeniedException] is thrown.
     * By default, [AuthenticationInfoProvider] methods are used to get user information like full name and avatar.
     *
     * @param principal obtained from [org.springframework.security.core.context.SecurityContext]
     * @return [UserInfo] with information about an authenticated user.
     */
    @PreAuthorize("isAuthenticated()")
    @QueryMapping("userInfo")
    fun userInfo(principal: Principal): UserInfo {
        //This implementation to get attribute values for an authenticated user can be changed
        val id: String = principal.name
        val fullName: String? = authenticationInfoProvider.fullName
        val avatar: String? = authenticationInfoProvider.avatar

        return UserInfo(id, fullName, avatar)
    }

    /**
     * Provides information about the authorities of an authenticated user. If there is no authenticated user, then [AccessDeniedException] is thrown.
     *
     * @param principal principal obtained from [org.springframework.security.core.context.SecurityContext]
     * @return a list that contains the authorities of an authenticated user.
     */
    @PreAuthorize("isAuthenticated()")
    @QueryMapping("userPermissions")
    fun userPermissions(principal: Principal): List<String> {
        if (principal is Authentication) {
            return principal.authorities
                .map { grantedAuthority -> grantedAuthority.authority}
                .toList()
        }
        return emptyList()
    }

    /**
     * Contains information about user.
     */
    class UserInfo {
        /**
         * User identifier.
         */
        @NotNull
        private var id: String = ""
        /**
         * User display name, value should be human-readable. E.g. full name or username.
         */
        private var fullName: String? = null
        /**
         * User's profile picture as URL or Base64 string.
         */
        private var avatar: String? = null

        constructor()
        constructor(id: String, fullName: String?, avatar: String?) {
            this.id = id
            this.fullName = fullName
            this.avatar = avatar
        }
    }
}
