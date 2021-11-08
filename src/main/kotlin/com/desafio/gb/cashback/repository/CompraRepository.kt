package com.desafio.gb.cashback.repository

import com.desafio.gb.cashback.entity.Compra
import org.springframework.data.jpa.repository.JpaRepository

interface CompraRepository : JpaRepository<Compra, Long> {
}