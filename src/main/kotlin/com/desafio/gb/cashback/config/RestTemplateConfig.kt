package com.desafio.gb.cashback.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

class RestTemplateConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}