package com.codesmith.phonebooking.enums

enum class BookingStatus(private val status: Int) {

    BOOKED(1),
    RETURNED(0);

    fun value(): Int {
        return this.status
    }

}