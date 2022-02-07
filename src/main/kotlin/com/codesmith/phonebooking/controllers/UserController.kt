package com.codesmith.phonebooking.controllers

import com.codesmith.phonebooking.entities.User
import com.codesmith.phonebooking.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(val userService: UserService) {

    @PostMapping("user")
    fun addUser(@RequestBody user: User): Any? {
        return userService.addUser(user)
    }

    @GetMapping("user/{userId}")
    fun getUser(@PathVariable("userId") userId: Int): Any? {
        return userService.getUser(userId)
    }

    @GetMapping
    fun getUsers(): Any? {
        return userService.getUsers()
    }

    @GetMapping("history/{userId}")
    fun getUserBookingHistory(@PathVariable("userId") userId: Int): Any? {
        return userService.getUserBookingHistory(userId)
    }


}