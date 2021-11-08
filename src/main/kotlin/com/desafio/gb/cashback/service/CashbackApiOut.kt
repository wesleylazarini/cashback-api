package com.desafio.gb.cashback.service

import org.springframework.stereotype.Service

@Service
interface CashbackApiOut {
    fun buscaTotalCashbackAcumulado(cpf: Long): Long
}