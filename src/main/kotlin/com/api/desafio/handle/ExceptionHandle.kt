package com.api.desafio.handle

import com.api.desafio.exception.SQLException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandle : ResponseEntityExceptionHandler(){

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val listErrors = mutableListOf<String>()
        ex.allErrors?.forEach {
            it?.let { error ->
                listErrors.add("Mensagem: ${error.defaultMessage}")
            }
        }
        return ResponseEntity.badRequest().body(mutableMapOf(Pair("Erros: ", listErrors)))
    }

    @ExceptionHandler(value = [SQLException::class])
    @ResponseStatus(value =  HttpStatus.UNPROCESSABLE_ENTITY)
    fun sqlException(sqlException: SQLException) = ResponseEntity.unprocessableEntity().body(sqlException.message)

}