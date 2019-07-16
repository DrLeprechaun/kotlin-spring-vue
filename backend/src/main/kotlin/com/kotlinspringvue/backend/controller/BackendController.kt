package com.kotlinspringvue.backend.controller

import org.springframework.web.bind.annotation.*
import com.kotlinspringvue.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.kotlinspringvue.backend.jpa.Person
import com.kotlinspringvue.backend.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api")
class BackendController() {

    @Autowired
    lateinit var personRepository: PersonRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()
}