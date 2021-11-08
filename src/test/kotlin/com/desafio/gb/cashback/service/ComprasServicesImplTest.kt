package com.desafio.gb.cashback.service

import com.desafio.gb.cashback.entity.dto.CompraDtoIn
import com.desafio.gb.cashback.entity.dto.RevendedorDto
import com.desafio.gb.cashback.entity.dto.toDto
import com.desafio.gb.cashback.entity.toEntity
import com.desafio.gb.cashback.repository.CompraRepository
import com.desafio.gb.cashback.service.impl.CompraServiceImpl
import java.time.LocalDate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ComprasServicesImplTest() {

    private lateinit var subject: CompraServiceImpl

    private val compraRepoTest: CompraRepository = mock(CompraRepository::class.java)
    private val revenServiceTest: RevendedorService = mock(RevendedorService::class.java)

    @BeforeEach
    fun setup() {
        subject = CompraServiceImpl(compraRepoTest, revenServiceTest)
    }

    @Test
    fun cadastroCompra() {

        val compraDtoIn = CompraDtoIn("ABC123", 1234.56, LocalDate.now(), "123.456.789-00")
        val revendedorDto = RevendedorDto(
            nome = "Fulano de Tal",
            cpf = compraDtoIn.cpf,
            email = "fulado@detal.com",
            senha = "Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq"
        )
        val compraDto = compraDtoIn.toDto(revendedorDto)

        `when`(revenServiceTest.buscaPorCpf(compraDtoIn.cpf)).thenReturn(revendedorDto)

        `when`(subject.cadastrarCompra(compraDtoIn))

        verify(compraRepoTest.save(compraDto.toEntity()), times(1))
    }
}