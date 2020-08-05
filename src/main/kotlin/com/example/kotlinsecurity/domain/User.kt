package com.example.kotlinsecurity.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document("users")
@TypeAlias("users")
class User(
        @Id
        var id: String? = null,
        var username: String? = null,
        var password: String? = null,
        @DBRef
        var roles: MutableSet<Role>? = HashSet(),
        var profile: String? = null,
        var active: Boolean? = true
) : Serializable {
    override fun toString(): String {
        return "User(id=$id, username=$username, password=$password, roles=$roles, profile=$profile, active=$active)"
    }
}
