package com.codesmith.phonebooking.config

import java.util.*
import javax.validation.MessageInterpolator


class CustomInterpolator(defaultMessageInterpolator: MessageInterpolator) : MessageInterpolator {
    private var defaultInterpolator: MessageInterpolator? = null

    fun MyMessageInterpolator(interpolator: MessageInterpolator?) {
        defaultInterpolator = interpolator
    }

    override fun interpolate(messageTemplate: String, context: MessageInterpolator.Context?): String? {
        var messageTemplate = messageTemplate
        messageTemplate = messageTemplate.uppercase(Locale.getDefault())
        return defaultInterpolator!!.interpolate(messageTemplate, context)
    }

    override fun interpolate(messageTemplate: String, context: MessageInterpolator.Context?, locale: Locale?): String? {
        var messageTemplate = messageTemplate
        messageTemplate = messageTemplate.uppercase(Locale.getDefault())
        return defaultInterpolator!!.interpolate(messageTemplate, context, locale)
    }
}