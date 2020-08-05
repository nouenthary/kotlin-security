package com.example.kotlinsecurity.domain

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "roles")
@TypeAlias("roles")
class Role(
        var id: String? = null,
        var name: ERole? = null
) : Serializable {
    override fun toString(): String {
        return "Role(name=$name)"
    }
}