package com.desafio.gb.cashback.entity

import com.desafio.gb.cashback.entity.dto.CompraDto
import com.desafio.gb.cashback.entity.dto.CompraDtoOut
import com.desafio.gb.cashback.entity.enuns.StatusCompraEnum
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "compras")
data class Compra(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val codigo: String,
    @Column
    val valor: Double,
    @Column
    val dataCompra: LocalDate,
    @Column(name = "status_compra")
    @Enumerated(EnumType.STRING)
    val statusCompra: StatusCompraEnum,
    @ManyToOne
    val revendedor: Revendedor,
    @Column
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
)

fun CompraDto.toEntity(): Compra {
    return Compra(
        codigo = this.codigo,
        valor = this.valor,
        dataCompra = this.dataCompra,
        revendedor = this.revendedor.toEntity(),
        statusCompra = this.statusCompra
    )
}

fun Compra.toDto(): CompraDto {
    return CompraDto(
        codigo = this.codigo,
        valor = this.valor,
        dataCompra = this.dataCompra,
        revendedor = this.revendedor.toDto(),
        statusCompra = this.statusCompra
    )
}
