package com.codesmith.phonebooking.config

import org.springframework.context.annotation.Configuration
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import javax.validation.Validation

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurationSupport() {

    override fun getValidator(): Validator? {
        val factory = LocalValidatorFactoryBean()
        factory.messageInterpolator =
            CustomInterpolator(Validation.byDefaultProvider().configure().defaultMessageInterpolator)
        return factory
    }

}