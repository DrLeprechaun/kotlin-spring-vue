package com.kotlinspringvue.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class NewUser : Serializable {

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("firstName")
    var firstName: String? = null

    @JsonProperty("lastName")
    var lastName: String? = null

    @JsonProperty("email")
    var email: String? = null

    @JsonProperty("password")
    var password: String? = null

    @JsonProperty("recapctha_token")
    var recaptchaToken: String? = null

    constructor() {}

    constructor(username: String, firstName: String, lastName: String, email: String, password: String, recaptchaToken: String) {
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.recaptchaToken = recaptchaToken
    }

    companion object {

        private const val serialVersionUID = -1764970284520387975L
    }
}