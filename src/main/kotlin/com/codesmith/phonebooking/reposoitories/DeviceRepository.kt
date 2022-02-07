package com.codesmith.phonebooking.reposoitories

import com.codesmith.phonebooking.entities.Device
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<Device, Int> {
}