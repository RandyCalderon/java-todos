package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByName(String name);
}
