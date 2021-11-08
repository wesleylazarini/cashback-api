package com.desafio.gb.cashback.service

import com.desafio.gb.cashback.entity.dto.RevendedorDto
import org.springframework.stereotype.Service

interface RevendedorService {
    fun cadastro(revendedorDto: RevendedorDto)
    fun buscaPorCpf(cpf: String): RevendedorDto
    fun buscaPoremail(email: String): RevendedorDto?
}