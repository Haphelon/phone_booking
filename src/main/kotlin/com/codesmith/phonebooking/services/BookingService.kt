package com.codesmith.phonebooking.services

import com.codesmith.phonebooking.entities.Booking
import com.codesmith.phonebooking.entities.Device
import com.codesmith.phonebooking.entities.User
import com.codesmith.phonebooking.enums.BookingStatus
import com.codesmith.phonebooking.reposoitories.BookingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class BookingService(
    val bookingRepository: BookingRepository,
    val deviceService: DeviceService,
    val userService: UserService
) {

    fun bookDevice(booking: Booking): MutableMap<String, String?> {
        val res: MutableMap<String, String?> = HashMap()
        return try {
            val device = booking.device ?: throw Exception("Please provide a device!")

            if (isDeviceBooked(device)) {
                throw Exception("Device is already booked!")
            }

            booking.setBookingStatus(BookingStatus.BOOKED)
            booking.bookedOn = Date()

            val booked = bookingRepository.save(booking)
            res["message"] =
                "Device booked successfully!"
            res
        } catch (ex: Exception) {
            ex.printStackTrace()
            res["message"] = ex.message
            res
        }
    }

    fun bookDeviceByDeviceIdAndUserId(deviceId: Int, userId: Int): MutableMap<String, String?> {
        val res: MutableMap<String, String?> = HashMap()
        return try {
            val device = deviceService.getDevice(deviceId)
            if (device !is Device) {
                if (device is String) {
                    throw Exception(device)
                }
            }

            val user: Any? = userService.getUser(userId)
            if (user !is User) {
                if (user is String) {
                    throw Exception(user)
                }
            }

            if (isDeviceBooked(device as Device)) {
                throw Exception("Device is already booked!")
            }

            val booking = Booking()
            booking.device = device as Device?
            booking.user = user as User?
            booking.bookedOn = Date()
            booking.setBookingStatus(BookingStatus.BOOKED)

            bookingRepository.save(booking)

            res["message"] =
                "Device ${booking.device?.name} booked successfully by ${booking.user?.firstName} ${booking.user?.lastName}"
            res

        } catch (ex: Exception) {
            ex.printStackTrace()
            res["message"] = ex.message
            res
        }
    }

    fun returnDevice(bookingId: Int): MutableMap<String, String?> {
        val res: MutableMap<String, String?> = HashMap()
        return try {
            val booking =
                bookingRepository.findByIdOrNull(bookingId) ?: throw Exception("No booking found by the provided ID!")
            booking.setBookingStatus(BookingStatus.RETURNED)
            booking.bookedOn = Date()

            bookingRepository.save(booking)
            res["message"] =
                "Device ${booking.device?.name} returned successfully by ${booking.user?.firstName} ${booking.user?.lastName}"
            res
        } catch (ex: Exception) {
            ex.printStackTrace()
            res["message"] = ex.message
            res
        }
    }

    fun returnDeviceByDeviceIdAndUserId(deviceId: Int, userId: Int): MutableMap<String, String?> {
        return try {
            val booking =
                bookingRepository.findByDeviceIdAndUserIdAndStatus(deviceId, userId, BookingStatus.BOOKED.value())
                    ?: throw Exception("No booking found by the provided ID!")
            return returnDevice(booking.bookingId!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
            val res: MutableMap<String, String?> = HashMap()
            res["message"] = ex.message
            res
        }
    }

    fun returnDeviceByDeviceId(deviceId: Int): MutableMap<String, String?> {
        return try {
            val booking = bookingRepository.findByDeviceIdAndStatus(deviceId, BookingStatus.BOOKED.value())
                ?: throw Exception("No booking found by the provided ID!")
            return returnDevice(booking.bookingId!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
            val res: MutableMap<String, String?> = HashMap()
            res["message"] = ex.message
            res
        }
    }

    fun getBookingDetailsByDeviceId(deviceId: Int): Any? {
        return try {
            deviceService.getDevice(deviceId);
        } catch (ex: Exception) {
            ex.printStackTrace()
            val res: MutableMap<String, String?> = HashMap()
            res["message"] = ex.message
            res
        }
    }

    fun isDeviceBooked(device: Device): Boolean {
        val booking = bookingRepository.findAllByDeviceAndStatus(device, BookingStatus.BOOKED.value())
        return booking.isNotEmpty()
    }

}