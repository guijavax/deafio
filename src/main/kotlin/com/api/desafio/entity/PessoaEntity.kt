package com.api.desafio.entity

import com.api.desafio.model.PessoaDTO
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "pessoas")
data class PessoaEntity(

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id :  UUID? = null,

    val apelido : String = "",

    val nome : String = "",

    @Column(name = "data_nascimento")
    val dataNascimento : String? = "",

    val stack : List<String> = mutableListOf()

)
fun PessoaEntity.toDTO() = PessoaDTO(idPessoa = id, dataNascimento = dataNascimento, apelido = apelido, nome = nome, stack =  stack)
