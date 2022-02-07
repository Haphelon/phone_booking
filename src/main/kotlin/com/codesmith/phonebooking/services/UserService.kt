package com.codesmith.phonebooking.services

import com.codesmith.phonebooking.entities.User
import com.codesmith.phonebooking.reposoitories.BookingRepository
import com.codesmith.phonebooking.reposoitories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val bookingRepository: BookingRepository
) {

    fun addUser(user: User): MutableMap<String, Any?> {
        val res: MutableMap<String, Any?> = HashMap()
        return try {
            if (user.firstName == null) {
                throw Exception("Please provide the user's first name")
            }

            if (user.lastName == null) {
                throw Exception("Please provide the user's last name")
            }
            userRepository.save(user)
            res["message"] = "${user.firstName} added successfully."
            res
        } catch (ex: Exception) {
            res["message"] = ex.message
            res
        }
    }

    fun getUser(userId: Int): Any? {
        return try {
            userRepository.findByIdOrNull(userId) ?: throw NoSuchElementException("User not found")
        } catch (ex: NoSuchElementException) {
            ex.message
        } catch (ex: Exception) {
            ex.printStackTrace()
            "An error occurred!"
        }
    }

    fun getUsers(): Any? {
        return try {
            userRepository.findAll()
        } catch (ex: NoSuchElementException) {
            ex.message
        } catch (ex: Exception) {
            ex.printStackTrace()
            "An error occurred!"
        }
    }

    fun getUserBookingHistory(userId: Int): Any? {
        return try {
            val user = userRepository.findByIdOrNull(userId) ?: throw Exception("No user found by the provided user id")
            bookingRepository.findAllByUser(user)
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.message
        }
    }
}