package com.dominio.todo.resources;

import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/todo")
public class TodoResource {

	@Autowired
	private TodoService todoService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<TodoResultDto> findById(@PathVariable Integer id) {
		TodoResultDto todo = todoService.findById(id);
		return ResponseEntity.ok(todo);
	}

}
