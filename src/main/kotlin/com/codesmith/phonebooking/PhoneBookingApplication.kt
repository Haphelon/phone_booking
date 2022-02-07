package com.codesmith.phonebooking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhoneBookingApplication

fun main(args: Array<String>) {
    runApplication<PhoneBookingApplication>(*args)
}
