package com.kotlinspringvue.backend.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.context.Context
import java.io.File
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage
import org.apache.commons.io.IOUtils
import org.springframework.core.env.Environment

@Component
class EmailServiceImpl : EmailService {

    @Value("\${spring.mail.username}")
    lateinit var sender: String

    @Autowired
    lateinit var environment: Environment

    @Autowired
    var emailSender: JavaMailSender? = null

    @Autowired
    lateinit var templateEngine: SpringTemplateEngine

    override fun sendSimpleMessage(to: String, subject: String, text: String) {
        try {
            val message = SimpleMailMessage()
            message.setTo(to)
            message.setFrom(sender)
            message.setSubject(subject)
            message.setText(text)

            emailSender!!.send(message)
        } catch (exception: MailException) {
            exception.printStackTrace()
        }

    }

    override fun sendSimpleMessageUsingTemplate(to: String,
                                                subject: String,
                                                template: String,
                                                params:MutableMap<String, Any>) {
        val message = emailSender!!.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "utf-8")
        var context: Context = Context()
        context.setVariables(params)
        val html: String = templateEngine.process(template, context)

        helper.setTo(to)
        helper.setFrom(sender)
        helper.setText(html, true)
        helper.setSubject(subject)

        emailSender!!.send(message)
    }

    override fun sendMessageWithAttachment(to: String,
                                           subject: String,
                                           text: String,
                                           pathToAttachment: String) {
        try {
            val message = emailSender!!.createMimeMessage()
            val helper = MimeMessageHelper(message, true)

            helper.setTo(to)
            helper.setFrom(sender)
            helper.setSubject(subject)
            helper.setText(text)

            val file = FileSystemResource(File(pathToAttachment))
            helper.addAttachment("Invoice", file)

            emailSender!!.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }

    }

    override fun sendHtmlMessage(to: String, subject: String, htmlMsg: String) {
        try {
            val message = emailSender!!.createMimeMessage()
            message.setContent(htmlMsg, "text/html")

            val helper = MimeMessageHelper(message, false, "utf-8")

            helper.setTo(to)
            helper.setFrom(sender)
            helper.setSubject(subject)

            emailSender!!.send(message)
        } catch (exception: MailException) {
            exception.printStackTrace()
        }
    }
}