package com.desafio.gb.cashback.entity.dto

data class TotalCashbackAcumuladoOut(
    val statusCode: Int,
    val body: BodyCredit
)

data class BodyCredit(
    val credit: Long = 0,
    val message: String? = ""
)