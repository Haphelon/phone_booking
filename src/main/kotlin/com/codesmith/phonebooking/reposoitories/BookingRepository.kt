package com.codesmith.phonebooking.reposoitories

import com.codesmith.phonebooking.entities.Booking
import com.codesmith.phonebooking.entities.Device
import com.codesmith.phonebooking.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookingRepository : JpaRepository<Booking, Int> {

    fun findAllByDeviceAndStatus(device: Device, status: Int): List<Booking>

    fun findByDeviceAndStatus(device: Device, status: Int): Booking?

    @Query("SELECT b FROM Booking b where b.device.deviceId = ?1 AND b.user.userId = ?2 AND b.status = ?3")
    fun findByDeviceIdAndUserIdAndStatus(deviceId: Int, userId: Int, status: Int): Booking?

    @Query("SELECT b FROM Booking b where b.device.deviceId = ?1 AND b.status = ?2")
    fun findByDeviceIdAndStatus(deviceId: Int, status: Int): Booking?

    fun findAllByDevice(device: Device): List<Booking>

    fun findAllByUser(user: User): List<Booking>

}