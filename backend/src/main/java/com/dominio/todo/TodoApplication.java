package com.dominio.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.dominio.todo.domain.Todo;
import com.dominio.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Todo t1 = new Todo(null, "Estudar", "Spring spring boot 2 e Angular 11", LocalDateTime.parse("01/08/2023 10:40", formatter), false);
		Todo t2 = new Todo(null, "Estudar", "Angular", LocalDateTime.parse("10/08/2023 10:40", formatter), false);
		Todo t3 = new Todo(null, "Estudar", "Deploy do backend no Heroku", LocalDateTime.parse("20/08/2023 10:40", formatter), false);
		Todo t4 = new Todo(null,"Estudar","Angular no Github pages", LocalDateTime.parse("22/08/2023 10:40", formatter),false);
		todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
	}
}
