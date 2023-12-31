package com.dominio.todo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.dominio.todo.domain.Todo;
import com.dominio.todo.repositories.TodoRepository;
import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.resources.dto.TodoInputDto;
import com.dominio.todo.services.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Todo todo = findTodoById(id);
		return modelMapper.map(todo, TodoResultDto.class);
	}

	@Transactional
	public TodoResultDto create(TodoInputDto todoInputDto) {
		Todo todo = modelMapper.map(todoInputDto, Todo.class);
		todo = todoRepository.save(todo);
		return modelMapper.map(todo, TodoResultDto.class);
	}

	@Transactional
	public TodoResultDto update(Integer id, TodoInputDto todoInputDto) {
		Todo todo = findTodoById(id);
		modelMapper.map(todoInputDto, todo);
		todo = todoRepository.save(todo);
		return modelMapper.map(todo, TodoResultDto.class);
	}

	@Transactional
	public void deleteById(Integer id) {
		try {
			todoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Todo not found", e.getCause());
		}
	}

	private Todo findTodoById(Integer id) {
		return todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
	}
}
