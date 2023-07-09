package com.dominio.todo.resources.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResultDto {

	private Integer id;
	private String titulo;
	private String descricao;
	private LocalDateTime dataParaFinalizar;
	private boolean finalizado;
}
