package com.kotlinspringvue.backend.web.error

class UserAlreadyExistException : RuntimeException {

    constructor() : super() {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    companion object {

        private val serialVersionUID = 5861310537366287163L
    }

}