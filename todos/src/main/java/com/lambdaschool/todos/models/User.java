package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long userid;

    @Column(nullable = false)
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    private Set<Todo> todo;

    public User() {

    }
}
