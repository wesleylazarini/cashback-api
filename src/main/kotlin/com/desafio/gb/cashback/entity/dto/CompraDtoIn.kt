package com.desafio.gb.cashback.entity.dto

import java.time.LocalDate

data class CompraDtoIn(
    val codigo: String,
    val valor: Double,
    val dataCompra: LocalDate,
    val cpf: String
)