package com.codesmith.phonebooking.reposoitories

import com.codesmith.phonebooking.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}