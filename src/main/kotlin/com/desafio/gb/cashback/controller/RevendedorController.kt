package com.desafio.gb.cashback.controller

import com.desafio.gb.cashback.entity.dto.RevendedorDto
import com.desafio.gb.cashback.service.RevendedorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/revendedor")
class RevendedorController(
    private val revendedorService: RevendedorService
) {

    @PostMapping
    fun cadastro(@RequestBody @Validated revendedor: RevendedorDto): ResponseEntity<Any> {

        revendedorService.cadastro(revendedor)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}