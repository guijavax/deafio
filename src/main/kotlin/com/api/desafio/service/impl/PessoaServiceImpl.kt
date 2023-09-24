package com.api.desafio.service.impl

import com.api.desafio.entity.PessoaEntity
import com.api.desafio.exception.SQLException
import com.api.desafio.model.PessoaDTO
import com.api.desafio.model.toEntity
import com.api.desafio.repository.PessoaRepository
import com.api.desafio.service.PessoaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PessoaServiceImpl : PessoaService {

    @Autowired
    lateinit var pessoaRepository: PessoaRepository

    override fun savePessoa(pessoaDTO: PessoaDTO) : UUID? {
        val pessoaEntity = pessoaDTO.toEntity()

        val exampleMatcher : Example<PessoaEntity> = Example.of(pessoaEntity)
        if (!pessoaRepository.exists(exampleMatcher)) {
            return pessoaRepository.save(pessoaEntity).id
        }
        throw SQLException("Pessoa já existe!")
    }

}