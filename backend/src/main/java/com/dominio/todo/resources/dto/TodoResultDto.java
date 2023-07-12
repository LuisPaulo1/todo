package com.dominio.todo.resources.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResultDto {
	private Integer id;
	private String titulo;
	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataParaFinalizar;
	private boolean finalizado;
}
