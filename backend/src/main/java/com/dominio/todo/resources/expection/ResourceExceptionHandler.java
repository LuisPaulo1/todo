package com.dominio.todo.resources.expection;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import com.dominio.todo.services.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> handleEntidadeNaoEncontrada(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = StandardError.builder()
				.dateTime(LocalDateTime.now())
				.status(status.value())
				.error("Recurso não encontrado")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.build();
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> handleErroDeSistema(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = StandardError.builder()
			.dateTime(LocalDateTime.now())
			.status(status.value())
			.error("Erro de sistema")
			.message(e.getMessage())
			.path(request.getRequestURI())
		.build();
		return ResponseEntity.status(status).body(err);
	}

}
