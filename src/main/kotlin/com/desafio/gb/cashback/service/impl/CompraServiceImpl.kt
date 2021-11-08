package com.desafio.gb.cashback.service.impl

import com.desafio.gb.cashback.entity.dto.CompraDto
import com.desafio.gb.cashback.entity.dto.CompraDtoIn
import com.desafio.gb.cashback.entity.dto.CompraDtoOut
import com.desafio.gb.cashback.entity.dto.toDto
import com.desafio.gb.cashback.entity.dto.toDtoOut
import com.desafio.gb.cashback.entity.enuns.BonificacaoCashbackEnum
import com.desafio.gb.cashback.entity.toDto
import com.desafio.gb.cashback.entity.toEntity
import com.desafio.gb.cashback.repository.CompraRepository
import com.desafio.gb.cashback.service.CompraService
import com.desafio.gb.cashback.service.RevendedorService
import java.util.stream.Collectors
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CompraServiceImpl(
    private val compraRepository: CompraRepository,
    private val revendedorService: RevendedorService
) : CompraService {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun cadastrarCompra(compraDtoIn: CompraDtoIn) {
        try {
            logger.info("m=cadastrarCompra c=CompraServiceImpl compraDtoIn=$compraDtoIn")

            val revendedor = revendedorService.buscaPorCpf(compraDtoIn.cpf)

            val compraDto = compraDtoIn.toDto(revendedor)

            compraRepository.save(compraDto.toEntity())
        } catch (ex: Exception) {
            logger.error("m=cadastrarCompra c=CompraServiceImpl message=${ex.message}")
            throw ex
        }
    }

    override fun listaCompras(page: Pageable): PageImpl<CompraDtoOut> {
        try {
            logger.info("m=listaCompras c=CompraServiceImpl page=$page")

            val compras = compraRepository.findAll(page)

            return PageImpl<CompraDtoOut>(
                compras.content
                    .stream()
                    .map { c -> calculaBonificacao(c.toDto()) }
                    .collect(Collectors.toList()), page, compras.totalElements
            )

        } catch (ex: Exception) {
            logger.error("m=listaCompras c=CompraServiceImpl message=${ex.message}")
            throw ex
        }
    }

    private fun calculaBonificacao(compra: CompraDto): CompraDtoOut {

        val bonificacao = if (compra.valor < 1000.0)
            BonificacaoCashbackEnum.DEZ.porcentagem
        else if (compra.valor < 1500.0)
            BonificacaoCashbackEnum.QUINZE.porcentagem
        else
            BonificacaoCashbackEnum.VINTE.porcentagem

        val valorBonificacao = ((bonificacao * compra.valor) + compra.valor)

        return compra.toDtoOut().copy(bonificacao = bonificacao.toDouble(), valorCashback = valorBonificacao)
    }
}