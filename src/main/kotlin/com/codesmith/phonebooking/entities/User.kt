package com.codesmith.phonebooking.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Int? = null

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "Please provide the user's first name")
    var firstName: String? = null

    @Column(name = "last_name", nullable = false)
    @NotNull(message = "Please provide the user's last name")
    var lastName: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "phone")
    var phone: String? = null

}