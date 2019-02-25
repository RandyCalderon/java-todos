package com.lambdaschool.todos;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path={}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodosController {

    @Autowired
    TodoRepository todorepos;

    @Autowired
    UserRepository userrepos;

    // Todo

    // All Todos
    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return todorepos.findAll();
    }

    // By Todoid
    @GetMapping("/todos/id/{id}")
    public Todo findTodoId(@PathVariable long id) {
        var foundTodo = todorepos.findById(id);
        if(foundTodo.isPresent()) {
            return foundTodo.get();
        } else {
            return null;
        }
    }

    // By todos not completed
    @GetMapping("/todos/active")
    public List<Object[]> todoFalse() {
        return todorepos.todoFalse();
    }

    // Post new todo
    @PostMapping("/todos")
    public Todo newTodo(@RequestBody Todo todo) {
        return todorepos.save(todo);
    }


    // User

    // All Users
    @GetMapping("/users")
    public List<User> allUsers() {
        return userrepos.findAll();
    }

    // By UserId
    @GetMapping("/users/id/{id}")
    public User findUserId(@PathVariable long id) {
        var foundUser = userrepos.findById(id);
        if(foundUser.isPresent()) {
            return foundUser.get();
        } else {
            return null;
        }
    }

    // By Username
    @GetMapping("/users/username/{username}")
    public User findByName(@PathVariable String name) {
        var foundName = userrepos.findByName(name);
        if(foundName != null) {
            return foundName;
        } else {
            return null;
        }
    }

    // Post new user
    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        return userrepos.save(user);
    }

    // Update existing User
    @PutMapping("/users/id/{id}")
    public User changeUser(@RequestBody User newUser, @PathVariable long id) throws URISyntaxException {
        Optional<User> updateUser = userrepos.findById(id);
        if(updateUser.isPresent()) {
            newUser.setUserid(id);
            userrepos.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

    // Delete existing user
    @DeleteMapping("/users/user/{userid}")
    public User deleteUser(@PathVariable long id) {
        var foundUser = userrepos.findById(id);
        if(foundUser.isPresent()) {
           userrepos.deleteById(id);
           return foundUser.get();
        } else {
            return null;
        }
    }
}
