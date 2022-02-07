package com.codesmith.phonebooking.entities

import com.codesmith.phonebooking.interfaces.BookingStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "booking")
class Booking : BookingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    var bookingId: Int? = null

    @JoinColumn(name = "device", referencedColumnName = "device_id", nullable = false)
    @ManyToOne
    var device: Device? = null

    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    @ManyToOne
    var user: User? = null

    @Column(name = "status")
    var status: Int? = null

    @Column(name = "booked_on")
    @Temporal(TemporalType.DATE)
    var bookedOn: Date? = null

    override fun setBookingStatus(status: com.codesmith.phonebooking.enums.BookingStatus) {
        this.status = status.value()
    }

}