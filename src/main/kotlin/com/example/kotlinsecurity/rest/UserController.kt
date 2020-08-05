package com.example.kotlinsecurity.rest

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    //    @PreAuthorize("hasRole('USER')")
    @ApiOperation(
            "ADMIN",
            authorizations = [Authorization("Authorization")]
    )
    @GetMapping("/users")
    fun user(): ResponseEntity<Any>? {
        return ResponseEntity.ok("users")
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(
            "ADMIN",
            authorizations = [Authorization("Authorization")]
    )
    @GetMapping("/admin")
    fun admin(): ResponseEntity<Any>? {
        return ResponseEntity.ok("admin")
    }

}