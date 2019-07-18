package com.kotlinspringvue.backend.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations
import org.springframework.beans.factory.annotation.Autowired
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest


@Service("captchaService")
class ReCaptchaService {

    val BASE_VERIFY_URL: String = "https://www.google.com/recaptcha/api/siteverify"

    @Autowired
    private val restTemplate: RestOperations? = null

    @Value("\${google.recaptcha.key.site}")
    lateinit var keySite: String

    @Value("\${google.recaptcha.key.secret}")
    lateinit var keySecret: String

    fun validateCaptcha(token: String): Boolean {

        val url: String = String.format(BASE_VERIFY_URL + "?secret=%s&response=%s", keySecret, token)

        val jsonResponse: HttpResponse<JsonNode> = Unirest.get(url)
                .header("accept", "application/json").queryString("apiKey", "123")
                .asJson()

        return (jsonResponse.getStatus() == 200)
    }

}