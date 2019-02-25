package com.lambdaschool.todos;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}
