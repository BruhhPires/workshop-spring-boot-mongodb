package com.brunopires.workshopmongo.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brunopires.workshopmongo.resources.exception.StandartError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundExceptions.class) // METODO PRA TRATATIVA DE ERROR "NOT FOUNDED" E/OU DIGITOU NUMERO INEXISTENTE
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundExceptions e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Não Encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
