package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
