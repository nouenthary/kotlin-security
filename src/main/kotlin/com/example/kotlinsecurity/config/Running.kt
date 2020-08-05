package com.example.kotlinsecurity.config

import com.example.kotlinsecurity.domain.ERole
import com.example.kotlinsecurity.domain.Role
import com.example.kotlinsecurity.respository.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class Running constructor(
        private val roleRepository: RoleRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val role1 = Role(null, ERole.ROLE_ADMIN)
        val role2 = Role(null, ERole.ROLE_USER)
        val role3 = Role(null, ERole.ROLE_GUEST)

        if (roleRepository.count().toInt() == 0) {
            roleRepository.saveAll(listOf(role1, role2, role3))
        }
    }
}