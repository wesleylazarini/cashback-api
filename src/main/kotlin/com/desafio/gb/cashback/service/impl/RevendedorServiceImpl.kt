package com.desafio.gb.cashback.service.impl

import com.desafio.gb.cashback.entity.dto.RevendedorDto
import com.desafio.gb.cashback.entity.toDto
import com.desafio.gb.cashback.entity.toEntity
import com.desafio.gb.cashback.repository.RevendedorRepository
import com.desafio.gb.cashback.service.RevendedorService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RevendedorServiceImpl(
    private val revendedorRepository: RevendedorRepository,
    private val encoder: PasswordEncoder
) : RevendedorService {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun cadastro(revendedorDto: RevendedorDto) {
        try {
            logger.info("m=cadastro c=RevendedorServiceImpl revendedorDto=$revendedorDto")

            val newRevendedorDto = revendedorDto.copy(senha = encoder.encode(revendedorDto.senha))

            revendedorRepository.save(newRevendedorDto.toEntity())
        } catch (ex: Exception) {
            logger.error("m=cadastro c=RevendedorServiceImpl message=${ex.message}")
        }
    }

    override fun buscaPorCpf(cpf: String): RevendedorDto {
        try {
            logger.info("m=buscaPorCpf c=RevendedorServiceImpl cpf=$cpf")

            val revendedor = revendedorRepository.findByCpf(cpf)

            logger.info("m=buscaPorCpf c=RevendedorServiceImpl revendedor=$revendedor")

            return revendedor.toDto()
        } catch (ex: Exception) {
            logger.error("m=cadastro c=RevendedorServiceImpl cpf=$cpf message=${ex.message}")
            throw ex
        }
    }

    override fun buscaPoremail(email: String): RevendedorDto? {
        try {
            logger.info("m=buscaPoremail c=RevendedorServiceImpl email=$email")

            val revendedor = revendedorRepository.findByEmail(email)

            logger.info("m=buscaPoremail c=RevendedorServiceImpl revendedor=$revendedor")

            return revendedor.toDto()
        } catch (ex: Exception) {
            logger.error("m=buscaPoremail c=RevendedorServiceImpl email=$email message=${ex.message}")
            throw ex
        }
    }
}