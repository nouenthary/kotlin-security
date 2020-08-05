package com.example.kotlinsecurity.domain

import java.io.Serializable

data class RequestLogin(
        val username: String? = null,
        val password: String? = null,
        val remember: Boolean? = false
) : Serializable {

    override fun toString(): String {
        return "RequestLogin(username=$username, password=$password, remember=$remember)"
    }
}
