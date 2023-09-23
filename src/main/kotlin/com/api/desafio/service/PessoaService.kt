package com.api.desafio.service

import com.api.desafio.entity.PessoaEntity
import com.api.desafio.model.PessoaDTO
import java.util.UUID

interface PessoaService {

    fun savePessoa(pessoaDTO: PessoaDTO) : UUID?
}