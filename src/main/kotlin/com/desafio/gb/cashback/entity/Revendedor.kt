package com.desafio.gb.cashback.entity

import com.desafio.gb.cashback.entity.dto.RevendedorDto
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "revendedores")
data class Revendedor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val nome: String,
    @Column
    val cpf: String,
    @Column
    val email: String,
    @Column
    val senha: String
)

fun RevendedorDto.toEntity(): Revendedor {
    return Revendedor(
        id = this.id,
        nome = this.nome,
        cpf = this.cpf,
        email = this.email,
        senha = this.senha
    )
}

fun Revendedor.toDto(): RevendedorDto {
    return RevendedorDto(
        id = this.id,
        nome = this.nome,
        cpf = this.cpf,
        email = this.email,
        senha = this.senha
    )
}
