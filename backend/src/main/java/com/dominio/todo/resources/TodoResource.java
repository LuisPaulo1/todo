package com.dominio.todo.resources;

import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api/todos")
public class TodoResource {

	@Autowired
	private TodoService todoService;

	@GetMapping
	public ResponseEntity<List<TodoResultDto>> listAll() {
		List<TodoResultDto> listTodos = todoService.findAll();
		return ResponseEntity.ok(listTodos);
	}

	@GetMapping(path = "/open")
	public ResponseEntity<List<TodoResultDto>> listAllOpen() {
		List<TodoResultDto> listTodosOpen = todoService.findAllOpenOrClose(false);
		return ResponseEntity.ok(listTodosOpen);
	}

	@GetMapping(path = "/close")
	public ResponseEntity<List<TodoResultDto>> listAllClose() {
		List<TodoResultDto> listTodosClose = todoService.findAllOpenOrClose(true);
		return ResponseEntity.ok(listTodosClose);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TodoResultDto> findById(@PathVariable Integer id) {
		TodoResultDto todo = todoService.findById(id);
		return ResponseEntity.ok(todo);
	}

}
