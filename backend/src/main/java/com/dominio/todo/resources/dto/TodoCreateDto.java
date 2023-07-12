package com.dominio.todo.resources.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDto {
	private String titulo;
	private String descricao;
	private LocalDate dataParaFinalizar;
}
