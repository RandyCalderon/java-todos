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
    @GetMapping("/todos/todo/{todoid}")
    public Todo findTodoId(@PathVariable long todoid) {
        var foundTodo = todorepos.findById(todoid);
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

    // Delete existing todo
    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodo(@PathVariable long todoid) {
        var foundTodo = todorepos.findById(todoid);
        if(foundTodo.isPresent()) {
            todorepos.deleteById(todoid);
            return foundTodo.get();
        } else {
            return null;
        }
    }

    // User

    // All Users
    @GetMapping("/users")
    public List<User> allUsers() {
        return userrepos.findAll();
    }

    // By UserId
    @GetMapping("/users/userid/{userid}")
    public User findUserId(@PathVariable long userid) {
        var foundUser = userrepos.findById(userid);
        if(foundUser.isPresent()) {
            return foundUser.get();
        } else {
            return null;
        }
    }

    // By Username
    @GetMapping("/users/username/{username}")
    public User findByName(@PathVariable String username) {
        var foundName = userrepos.findByName(username);
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
    @PutMapping("/users/userid/{userid}")
    public User changeUser(@RequestBody User newUser, @PathVariable long userid) throws URISyntaxException {
        Optional<User> updateUser = userrepos.findById(userid);
        if(updateUser.isPresent()) {
            newUser.setUserid(userid);
            userrepos.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

    // Delete existing user
    @DeleteMapping("/users/id/{userid}")
    public User deleteUser(@PathVariable long userid) {
        var foundUser = userrepos.findById(userid);
        if(foundUser.isPresent()) {
           userrepos.deleteById(userid);
           return foundUser.get();
        } else {
            return null;
        }
    }
}
