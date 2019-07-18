package com.kotlinspringvue.backend.controller

import org.springframework.web.bind.annotation.*
import com.kotlinspringvue.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.kotlinspringvue.backend.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import com.kotlinspringvue.backend.repository.UserRepository
import com.kotlinspringvue.backend.jpa.User

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class BackendController() {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var userRepository: UserRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()

    @GetMapping("/usercontent")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        val user: User = userRepository.findByUsername(authentication.name).get()
        return "Hello " + user.firstName + " " + user.lastName + "!"
    }

    @GetMapping("/admincontent")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    fun getAdminContent(): String {
        return "Admin's content"
    }
}