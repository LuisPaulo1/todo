package com.dominio.todo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Todo {

	@EqualsAndHashCode.Include
	private String id;
	private String titulo;
	private String descricao;
	private LocalDateTime dataParaFinalizar;
	private boolean finalizado;

}
