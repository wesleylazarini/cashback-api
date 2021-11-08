package com.desafio.gb.cashback.entity.dto

import com.desafio.gb.cashback.entity.enuns.StatusCompraEnum
import java.time.LocalDate
import java.time.LocalDateTime

data class CompraDto(
    val codigo: String,
    val valor: Double,
    val dataCompra: LocalDate,
    val revendedor: RevendedorDto,
    val statusCompra: StatusCompraEnum,
    val dataCriacao: LocalDateTime? = null
)

fun CompraDtoIn.toDto(revendedor: RevendedorDto): CompraDto {
    return CompraDto(
        codigo = this.codigo,
        valor = this.valor,
        dataCompra = this.dataCompra,
        statusCompra = whiteList(revendedor.cpf),
        revendedor = revendedor
    )
}

fun CompraDto.toDtoOut(): CompraDtoOut {
    return CompraDtoOut(
        codigo = this.codigo,
        valor = this.valor,
        dataCompra = this.dataCompra,
        statusCompra = this.statusCompra.desc,
        revendedor = revendedor
    )
}

private fun whiteList(cpf: String): StatusCompraEnum {
    return if ("15350946056" == cpf.replace("/,/g", "."))
        StatusCompraEnum.APROVADO
    else
        StatusCompraEnum.EM_VALIDACAO
}