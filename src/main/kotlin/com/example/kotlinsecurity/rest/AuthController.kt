package com.example.kotlinsecurity.rest

import com.example.kotlinsecurity.domain.RequestLogin
import com.example.kotlinsecurity.service.UserService
import com.example.kotlinsecurity.service.dto.UserDTO
import com.example.kotlinsecurity.service.UserDetailsImpl
import com.example.kotlinsecurity.service.jwt.JwtResponse
import com.example.kotlinsecurity.service.jwt.JwtUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/auth")
class AuthController constructor(
        private val userService: UserService,

        private val authenticationManager: AuthenticationManager,
        private val jwtUtils: JwtUtils
) {

    @PostMapping("/authenticate")
    fun login(@RequestBody requestLogin: RequestLogin): ResponseEntity<Any?> {
        val authentication: Authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                requestLogin.username,
                requestLogin.password
        ))
        SecurityContextHolder.getContext().authentication = authentication

        val jwt = jwtUtils.generateJwtToken(authentication)

        val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl

        val roles: MutableList<String>? = userDetails.authorities.stream().map { it.authority }.collect(Collectors.toList())

        val jwtResponse = JwtResponse(userDetails.getId(), userDetails.username, roles, "Bearer", jwt)

        return ResponseEntity.ok(jwtResponse)
    }

    @PostMapping("/register")
    fun register(@RequestBody userDTO: UserDTO): ResponseEntity<Any?> {
        return try {
            val users = userService.creatUser(userDTO)
            ResponseEntity(users, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/currentUser")
    fun getCurrentUser(): ResponseEntity<Any?> {
        val auth = SecurityContextHolder.getContext().authentication
        return ResponseEntity.ok("auth : $auth")
    }
}