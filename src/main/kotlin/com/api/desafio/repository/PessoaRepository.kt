package com.api.desafio.repository

import com.api.desafio.entity.PessoaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<PessoaEntity, Long>{
}