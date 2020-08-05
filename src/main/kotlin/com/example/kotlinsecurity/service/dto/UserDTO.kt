package com.example.kotlinsecurity.service.dto

import com.example.kotlinsecurity.domain.Role


open class UserDTO(
        var id: String? = null,
        var username: String? = null,
        var password: String? = null,
        var role: MutableSet<Role>? = null
) {
    override fun toString(): String {
        return "UserDTO(id=$id, username=$username, password=$password, role=$role)"
    }
}