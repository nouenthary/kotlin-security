package com.example.kotlinsecurity.service

import com.example.kotlinsecurity.domain.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsImpl : UserDetails {

    private var id: String? = null

    private var username: String? = null

    @JsonIgnore
    private var password: String? = null

    private var authorities: MutableCollection<out GrantedAuthority>? = null

    constructor(id: String?, username: String?, password: String?, authorities: MutableCollection<out GrantedAuthority>?) {
        this.id = id
        this.username = username
        this.password = password
        this.authorities = authorities
    }

    fun getId(): String {
        return id!!
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities!!
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return username!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    companion object {
        fun build(user: User): UserDetailsImpl {
            val authorities: MutableCollection<GrantedAuthority>? = user.roles!!.stream()
                    .map { SimpleGrantedAuthority(it.name!!.name) }.collect(Collectors.toList())
            return UserDetailsImpl(user.id, user.username, user.password, authorities)
        }
    }
}