package com.desafio.gb.cashback.service.impl

import com.desafio.gb.cashback.entity.dto.TotalCashbackAcumuladoOut
import com.desafio.gb.cashback.service.CashbackApiOut
import net.bytebuddy.implementation.bytecode.Throw
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CashbackApiOutImpl(
    @Value("\${cashback-api.url}")
    val url: String,
    @Value("\${cashback-api.token}")
    val token: String
) : CashbackApiOut {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscaTotalCashbackAcumulado(cpf: Long): Long {
        try {
            logger.info("m=buscaTotalCashbackAcumulado c=CashbackApiOutImpl")

            val headers = HttpHeaders();
            headers.set("token", token);

            val resp = RestTemplate()
                .exchange(
                    "$url$cpf",
                    HttpMethod.GET,
                    HttpEntity<TotalCashbackAcumuladoOut>(null, headers),
                    TotalCashbackAcumuladoOut::class.java
                ).body ?: throw Exception("Error request API")

            if (resp.statusCode != 200)
                throw Exception("Error na API: ${resp.body.message}")

            return resp.body.credit
        } catch (ex: Exception) {
            logger.error("m=buscaTotalCashbackAcumulado c=CashbackApiOutImpl message=${ex.message}")
            throw ex
        }
    }
}