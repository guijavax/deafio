package com.api.desafio.rest

import com.api.desafio.entity.PessoaEntity
import com.api.desafio.model.PessoaDTO
import com.api.desafio.service.PessoaService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.HashMap
import java.util.Objects
import java.util.UUID

@RestController
@RequestMapping(value = ["/pessoas"])
class PessoaController {

    @Autowired
    lateinit var pessoaService: PessoaService

    @PostMapping
    fun savePessoa(@Valid @RequestBody pessoaDTO: PessoaDTO ) : ResponseEntity<String> {

       val id =  pessoaService.savePessoa(pessoaDTO)
        val responseHttpHeaders = HttpHeaders()
        val location = URI.create("/pessoas/$id")
        responseHttpHeaders.location = location
        return ResponseEntity(responseHttpHeaders, HttpStatusCode.valueOf(201))
    }

    @GetMapping(value = ["/{idPessoa}"])
    fun findPessoaById(@PathVariable(name = "idPessoa") idPessoa : UUID) : ResponseEntity<PessoaDTO> {
        val pessoaDTO = pessoaService.findById(idPessoa)
        return if (Objects.nonNull(pessoaDTO)) ResponseEntity.ok(pessoaDTO) else ResponseEntity.notFound().build()
    }
}