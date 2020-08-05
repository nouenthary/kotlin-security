package com.example.kotlinsecurity.service

import com.example.kotlinsecurity.domain.User
import com.example.kotlinsecurity.respository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl constructor(
        private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userRepository.findByUsername(username)
                .orElseThrow { throw UsernameNotFoundException("User Not Found with username: $username") }
        return UserDetailsImpl.build(user)
    }
}