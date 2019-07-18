package com.kotlinspringvue.backend.jwt

import java.io.IOException

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthEntryPoint: AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          e: AuthenticationException) {

        logger.error("Unauthorized error. Message - {}", e!!.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthEntryPoint::class.java)
    }
}