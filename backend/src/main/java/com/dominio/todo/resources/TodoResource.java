package com.dominio.todo.resources;

import com.dominio.todo.resources.dto.TodoResultDto;
import com.dominio.todo.resources.dto.TodoInputDto;
import com.dominio.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
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

	@PostMapping
	public ResponseEntity<TodoResultDto> create(@RequestBody TodoInputDto todoInputDto) {
		TodoResultDto newTodo = todoService.create(todoInputDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
		return ResponseEntity.created(uri).body(newTodo);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<TodoResultDto> update(@PathVariable Integer id, @RequestBody TodoInputDto todoInputDto) {
		TodoResultDto todoUpdated = todoService.update(id, todoInputDto);
		return ResponseEntity.ok(todoUpdated);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
