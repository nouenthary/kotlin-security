package com.example.kotlinsecurity.service.jwt

import com.example.kotlinsecurity.service.UserDetailsServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter : OncePerRequestFilter() {

    private val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)

    @Autowired
    private val jwtUtils: JwtUtils? = null

    @Autowired
    private val userDetailsServiceImpl: UserDetailsServiceImpl? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = parseJwt(request)
            if (jwt != null && jwtUtils!!.validateJwtToken(jwt)) {
                val username = jwtUtils.getUserNameFromJwtToken(jwt)
                val userService: UserDetails = userDetailsServiceImpl!!.loadUserByUsername(username)
                val authenticate = UsernamePasswordAuthenticationToken(userService, null, userService.authorities)
                authenticate.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticate
            }

        } catch (e: Exception) {
            //logger.error("Cannot set user authentication: {}", e)
            println("Cannot set user authentication: {} $e")
        }

        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length)
        }
        return null
    }
}