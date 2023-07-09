package com.dominio.todo.services;

import com.dominio.todo.domain.Todo;
import com.dominio.todo.repositories.TodoRepository;
import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.services.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public TodoResultDto findById(Integer id) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
		return modelMapper.map(todo, TodoResultDto.class);
	}

}
