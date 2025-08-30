package com.learn.restapibasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 *  first line of defence
 * responsibilities
 *   collect request
 *   send response - whatever we have returned that is automatically converted into response object
 * */
@RestController
public class TodoController {
    private static List<Todo> todoList;


    public TodoController(){
        todoList = new ArrayList<>();

        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
        todoList.add(new Todo(3, true, "Todo 2", 2));
        todoList.add(new Todo(4, true, "Todo 2", 1));
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoList;
    }
}
