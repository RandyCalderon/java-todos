package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "SELECT t.completed FROM todo t, WHERE t.completed = 0", nativeQuery = true)
    List<Object[]> todoFalse();
}
