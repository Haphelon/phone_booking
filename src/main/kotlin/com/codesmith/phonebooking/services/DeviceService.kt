package com.codesmith.phonebooking.services

import com.codesmith.phonebooking.entities.Device
import com.codesmith.phonebooking.enums.BookingStatus
import com.codesmith.phonebooking.pojos.Devices
import com.codesmith.phonebooking.reposoitories.BookingRepository
import com.codesmith.phonebooking.reposoitories.DeviceRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.util.retry.Retry

@Service
class DeviceService(
    val deviceRepository: DeviceRepository,
    val bookingRepository: BookingRepository
) {

    @Value("\${mock-server.url.devices}")
    private lateinit var mockServerUrl: String

    fun addDevice(device: Device): MutableMap<String, String?> {
        val res: MutableMap<String, String?> = HashMap()
        return try {
            deviceRepository.save(device)
            res["message"] = "${device.name} added successfully."
            res
        } catch (ex: Exception) {
            ex.printStackTrace()
            res["message"] = ex.message
            res
        }
    }

    fun getDevice(deviceId: Int): Any? {
        return try {
            val device =
                deviceRepository.findByIdOrNull(deviceId) ?: throw Exception("No device with the ID provided found!")
            val booking = bookingRepository.findByDeviceAndStatus(device, BookingStatus.BOOKED.value())
            device.available = "YES"
            booking?.let {
                device.available = "NO"
                device.bookedBy = it.user
                device.bookedOn = it.bookedOn
            }
            val devices: Devices? = WebClient.builder().build().get()
                .uri(mockServerUrl)
                .retrieve()
                .bodyToMono(Devices::class.java)
                .block()

            val deviceDetails: com.codesmith.phonebooking.pojos.Device? =
                devices?.devices?.find { it.deviceName.equals(device.name) }
            deviceDetails?.let {
                device._2GBands = it._2g_bands
                device._3GBands = it._3g_bands
                device._4GBands = it._4g_bands
                device.technology = it.technology
            }
            device
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.message
        }
    }

    fun getDevices(): Any? {
        return try {
            var devices = deviceRepository.findAll()
            devices = devices.map {
                val booking = bookingRepository.findByDeviceAndStatus(it, BookingStatus.BOOKED.value())
                it.available = "NO"
                booking?.let { it1 ->
                    it.available = "YES"
                    it.bookedBy = it1.user
                    it.bookedOn = it1.bookedOn
                }
                it
            }
            devices
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.message
        }
    }

    fun getDeviceBookingHistory(deviceId: Int): Any? {
        return try {
            val device =
                deviceRepository.findByIdOrNull(deviceId) ?: throw Exception("No device with the ID provided found!")
            val bookingHistory = bookingRepository.findAllByDevice(device)
            bookingHistory
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.message
        }
    }

}