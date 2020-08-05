package com.example.kotlinsecurity.respository

import com.example.kotlinsecurity.domain.ERole
import com.example.kotlinsecurity.domain.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : MongoRepository<Role, String> {

    fun findByName(eRole: ERole): Optional<Role>
}