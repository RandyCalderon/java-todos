package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
   @Query(value = "SELECT * FROM USERS WHERE name = :name", nativeQuery = true)
    User findByName(@Param("name") String name);
}
