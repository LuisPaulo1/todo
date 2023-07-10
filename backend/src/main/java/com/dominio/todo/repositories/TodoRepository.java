package com.dominio.todo.repositories;

import com.dominio.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByFinalizadoOrderByDataParaFinalizar(boolean finalizado);
}
