package com.example.kotlinsecurity.service.jwt

import java.io.Serializable

data class JwtResponse(
        var id: String? = null,
        var username: String? = null,
        var roles: MutableList<String>? = null,
        var tokenType: String? = null,
        var accessTokenType: String? = null
) : Serializable