package com.dominio.todo.services;

import com.dominio.todo.domain.Todo;
import com.dominio.todo.repositories.TodoRepository;
import com.dominio.todo.resources.dto.TodoCreateDto;
import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.services.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<TodoResultDto> findAll() {
		List<Todo> listTodos = todoRepository.findAll();
		return listTodos.stream()
				.map(todo -> modelMapper.map(todo, TodoResultDto.class))
				.collect(Collectors.toList());
	}

	public List<TodoResultDto> findAllOpenOrClose(boolean finalizado) {
		List<Todo> listTodos = todoRepository.findAllByFinalizadoOrderByDataParaFinalizar(finalizado);
		return listTodos.stream()
				.map(todo -> modelMapper.map(todo, TodoResultDto.class))
				.collect(Collectors.toList());
	}

	public TodoResultDto findById(Integer id) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
		return modelMapper.map(todo, TodoResultDto.class);
	}

	public TodoResultDto create(TodoCreateDto todoCreateDto) {
		Todo todo = modelMapper.map(todoCreateDto, Todo.class);
		if(todoCreateDto.getDataParaFinalizar().isBefore(LocalDateTime.now())) {
			todo.setFinalizado(true);
		}
		todo = todoRepository.save(todo);
		return modelMapper.map(todo, TodoResultDto.class);
	}
}
