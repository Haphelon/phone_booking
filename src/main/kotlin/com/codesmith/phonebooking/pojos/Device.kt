package com.codesmith.phonebooking.pojos

import com.fasterxml.jackson.annotation.JsonProperty

class Device {

    @JsonProperty("DeviceName")
    var deviceName: String? = null

    @JsonProperty("Brand")
    var brand: String? = null
    var technology: String? = null
    var gprs: String? = null
    var edge: String? = null
    var announced: String? = null
    var status: String? = null
    var dimensions: String? = null
    var weight: String? = null
    var sim: String? = null
    var type: String? = null
    var size: String? = null
    var resolution: String? = null
    var display_c: String? = null
    var card_slot: String? = null
    var alert_types: String? = null
    var loudspeaker_: String? = null
    var sound_c: String? = null
    var wlan: String? = null
    var bluetooth: String? = null
    var gps: String? = null
    var radio: String? = null
    var usb: String? = null
    var messaging: String? = null
    var browser: String? = null
    var java: String? = null
    var features_c: String? = null
    var battery_c: String? = null
    var talk_time: String? = null
    var colors: String? = null
    var sar_us: String? = null
    var sar_eu: String? = null
    var sensors: String? = null
    var cpu: String? = null
    var internal: String? = null
    var os: String? = null
    var body_c: String? = null
    var primary_: String? = null
    var video: String? = null
    var secondary: String? = null
    var speed: String? = null
    var network_c: String? = null
    var chipset: String? = null
    var features: String? = null
    var music_play: String? = null
    var protection: String? = null
    var gpu: String? = null
    var multitouch: String? = null
    var loudspeaker: String? = null
    var audio_quality: String? = null
    var nfc: String? = null
    var camera: String? = null
    var display: String? = null
    var performance: String? = null
    var _2g_bands: String? = null
    var _3_5mm_jack_: String? = null
    var _3g_bands: String? = null
    var _4g_bands: String? = null

}