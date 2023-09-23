package com.api.desafio.model

import com.api.desafio.entity.PessoaEntity
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.util.UUID

data class PessoaDTO(

    val idPessoa : UUID? = null,

    @field:Size(max = 32, message = "Tamanho Válido até 32 caracteres!")
    val apelido : String,

    @field:Size(max = 100, message = "Tamanho Válido até 32 caracteres!")
    val nome : String,

    @field:Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de data inválido!")
    val dataNascimento : String? = "",

    val stack : List<String> = emptyList()
) {


}
fun PessoaDTO.toEntity() : PessoaEntity =
    PessoaEntity(apelido = apelido,
                nome = nome,
               dataNascimento = dataNascimento,
               stack = stack)