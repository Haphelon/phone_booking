package com.codesmith.phonebooking.controllers

import com.codesmith.phonebooking.entities.Booking
import com.codesmith.phonebooking.services.BookingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("booking")
class BookingController(
    val bookingService: BookingService
) {

    @PostMapping("book")
    fun bookDevice(@RequestBody booking: Booking): MutableMap<String, String?> {
        return bookingService.bookDevice(booking)
    }

    @PostMapping("book/{deviceId}/{userId}")
    fun bookDeviceByDeviceIdAndUserId(
        @PathVariable("deviceId") deviceId: Int,
        @PathVariable("userId") userId: Int
    ): MutableMap<String, String?> {
        return bookingService.bookDeviceByDeviceIdAndUserId(deviceId, userId)
    }

    @PutMapping("return/{bookingId}")
    fun returnDevice(@PathVariable("bookingId") bookingId: Int): MutableMap<String, String?> {
        return bookingService.returnDevice(bookingId)
    }

    @PutMapping("return/{deviceId}/{userId}")
    fun returnDeviceByDeviceIdAndUserId(
        @PathVariable("deviceId") deviceId: Int,
        @PathVariable("userId") userId: Int
    ): MutableMap<String, String?> {
        return bookingService.returnDeviceByDeviceIdAndUserId(deviceId, userId)
    }

    @PutMapping("return/device/{deviceId}")
    fun returnDeviceByDeviceId(@PathVariable("deviceId") deviceId: Int): MutableMap<String, String?> {
        return bookingService.returnDeviceByDeviceId(deviceId)
    }

    @GetMapping("booking-details/{deviceId}")
    fun getBookingDetailsByDeviceId(@PathVariable("deviceId") deviceId: Int): Any? {
        return bookingService.getBookingDetailsByDeviceId(deviceId)
    }

}