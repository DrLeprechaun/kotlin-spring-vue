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
import com.kotlinspringvue.backend.email.EmailServiceImpl
import com.kotlinspringvue.backend.web.response.ResponseMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class BackendController() {

    @Value("\${spring.mail.username}")
    lateinit var addressee: String

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var emailService: EmailServiceImpl

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

    @GetMapping("/sendSimpleEmail")
    @PreAuthorize("hasRole('USER')")
    fun sendSimpleEmail(): ResponseEntity<*> {
        try {
            //Uncomment to use
            //emailService.sendSimpleMessage(addressee, "Simple Email", "Hello! This is simple email")
        } catch (e: Exception) {
            return ResponseEntity(ResponseMessage("Error while sending message"), HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(ResponseMessage("Email has been sent"), HttpStatus.OK)
    }

    @GetMapping("/sendTemplateEmail")
    @PreAuthorize("hasRole('USER')")
    fun sendTemplateEmail(): ResponseEntity<*> {
        try {
            var params:MutableMap<String, Any> = mutableMapOf()
            params["addresseeName"] = addressee
            params["signatureImage"] = "https://coderlook.com/wp-content/uploads/2019/07/spring-by-pivotal.png"
            //Uncomment to use
            //emailService.sendSimpleMessageUsingTemplate(addressee, "Template Email", "emailTemplate", params)
        } catch (e: Exception) {
            return ResponseEntity(ResponseMessage("Error while sending message"), HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(ResponseMessage("Email has been sent"), HttpStatus.OK)
    }

    @GetMapping("/sendHtmlEmail")
    @PreAuthorize("hasRole('USER')")
    fun sendHtmlEmail(): ResponseEntity<*> {
        try {
            //Uncomment to use
            //emailService.sendHtmlMessage(addressee, "HTML Email", "<h1>Hello!</h1><p>This is HTML email</p>")
        } catch (e: Exception) {
            return ResponseEntity(ResponseMessage("Error while sending message"), HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(ResponseMessage("Email has been sent"), HttpStatus.OK)
    }
}