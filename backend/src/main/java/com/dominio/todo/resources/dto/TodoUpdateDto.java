package com.dominio.todo.resources.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoUpdateDto {
	private String titulo;
	private String descricao;
	private LocalDateTime dataParaFinalizar;
	private boolean finalizado;
}
