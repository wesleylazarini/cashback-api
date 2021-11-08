package com.desafio.gb.cashback.service

import com.desafio.gb.cashback.entity.dto.CompraDtoIn
import com.desafio.gb.cashback.entity.dto.CompraDtoOut
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

interface CompraService {
    fun cadastrarCompra(compraDto: CompraDtoIn)
    fun listaCompras(page: Pageable): PageImpl<CompraDtoOut>
}