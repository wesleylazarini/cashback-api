package com.desafio.gb.cashback.entity.dto

import java.time.LocalDate

data class CompraDtoOut(
    val codigo: String,
    val valor: Double,
    val dataCompra: LocalDate,
    val bonificacao: Double? = 0.0,
    val valorCashback: Double? = 0.0,
    val statusCompra: String,
    val revendedor: RevendedorDto
)