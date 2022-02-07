package com.codesmith.phonebooking.controllers

import com.codesmith.phonebooking.entities.Device
import com.codesmith.phonebooking.services.DeviceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("devices")
class DeviceController(val deviceService: DeviceService) {

    @PostMapping("device")
    fun addDevice(@RequestBody device: Device): MutableMap<String, String?> {
        return deviceService.addDevice(device)
    }

    @GetMapping("device/{deviceId}")
    fun getDevice(@PathVariable("deviceId") deviceId: Int): Any? {
        return deviceService.getDevice(deviceId)
    }

    @GetMapping
    fun getDevices(): Any? {
        return deviceService.getDevices()
    }

    @GetMapping("history/{deviceId}")
    fun getDeviceBookingHistory(@PathVariable("deviceId") deviceId: Int): Any? {
        return deviceService.getDeviceBookingHistory(deviceId)
    }
}