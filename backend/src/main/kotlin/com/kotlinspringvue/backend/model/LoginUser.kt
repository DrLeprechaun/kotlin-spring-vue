package com.kotlinspringvue.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class LoginUser : Serializable {

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("password")
    var password: String? = null

    @JsonProperty("recapctha_token")
    var recaptchaToken: String? = null

    constructor() {}

    constructor(username: String, password: String, recaptchaToken: String) {
        this.username = username
        this.password = password
        this.recaptchaToken = recaptchaToken
    }

    companion object {

        private const val serialVersionUID = -1764970284520387975L
    }
}