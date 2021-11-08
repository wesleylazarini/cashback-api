package com.desafio.gb.cashback.entity.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF


data class RevendedorDto(
    val id: Long? = null,
    @NotBlank(message = "Nome é obrigatorio")
    val nome: String,
    @CPF(message = "CPF é obrigatorio")
    val cpf: String,
    @Email(message = "E-mail é obrigatorio")
    val email: String,
    @NotBlank(message = "Senha é obrigatorio")
    val senha: String
)