package com.example.kotlinsecurity.service

import com.example.kotlinsecurity.domain.User
import com.example.kotlinsecurity.respository.UserRepository
import com.example.kotlinsecurity.service.dto.UserDTO
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun creatUser(userDTO: UserDTO): User? {
        val newUser = User(
                id = null,
                username = userDTO.username,
                password = passwordEncoder.encode(userDTO.password),
                active = true,
                profile = "image.jpg",
                roles = userDTO.role
        )
        userRepository.save(newUser)
        return newUser
    }
}