package com.dominio.todo.resources.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoCreateDto {
	private String titulo;
	private String descricao;
	private LocalDateTime dataParaFinalizar;
}
