package com.desafio.gb.cashback.controller

import com.desafio.gb.cashback.entity.dto.CompraDtoIn
import com.desafio.gb.cashback.service.CashbackApiOut
import com.desafio.gb.cashback.service.CompraService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/compra")
class CompraController(
    private val compraService: CompraService,
    private val cashbackApiOut: CashbackApiOut
) {

    @PostMapping
    fun cadastro(@RequestBody @Validated compraDto: CompraDtoIn): ResponseEntity<Any> {

        compraService.cadastrarCompra(compraDto)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping
    fun buscaCompras(
        @PageableDefault(
            page = 0, size = 10, sort =
            ["dataCompra"], direction = Sort.Direction.DESC
        ) page: Pageable
    ): ResponseEntity<Any> {

        val listaCompra = compraService.listaCompras(page)

        return ResponseEntity.ok(listaCompra)
    }

    @GetMapping("/cashback/{cpf}")
    fun buscaTotalCashback(@PathVariable("cpf") cpf: Long): ResponseEntity<Any> {

        val totalCashback = cashbackApiOut.buscaTotalCashbackAcumulado(cpf)

        return ResponseEntity.ok("{\"totalAcumulado\": $totalCashback}")
    }

}