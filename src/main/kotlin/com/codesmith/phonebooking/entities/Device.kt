package com.codesmith.phonebooking.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "device")
class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", nullable = false)
    var deviceId: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Transient
    var technology: String? = null

    @Transient
    var _2GBands: String? = null

    @Transient
    var _3GBands: String? = null

    @Transient
    var _4GBands: String? = null

    @Transient
    var available: String? = null

    @Transient
    var bookedOn: Date? = null

    @Transient
    var bookedBy: User? = null

}