package com.desafio.gb.cashback.repository

import com.desafio.gb.cashback.entity.Compra
import com.desafio.gb.cashback.entity.Revendedor
import org.springframework.data.jpa.repository.JpaRepository

interface RevendedorRepository : JpaRepository<Revendedor, Long> {
    fun findByCpf(cpf: String): Revendedor
    fun findByEmail(email: String): Revendedor
}