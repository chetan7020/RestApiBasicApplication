package com.learn.restapibasic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
 *  first line of defence
 * responsibilities
 *   collect request
 *   send response - whatever we have returned that is automatically converted into response object
 * */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;


    public TodoController(){
        todoList = new ArrayList<>();

        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
        todoList.add(new Todo(3, true, "Todo 2", 2));
        todoList.add(new Todo(4, true, "Todo 2", 1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Todo>>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todoList, null));
    }

    @GetMapping("/{todoID}")
    public ResponseEntity<ApiResponse<Todo>> getTodo(@PathVariable int todoID){
        for(Todo todo : todoList){
            if(todo.getId() == todoID){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todo, null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "todo with id : " + todoID + " not found"));
    }

    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED) - can be used to set status code in case of succeses
    public ResponseEntity<ApiResponse<Todo>> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, newTodo, null));
    }
}
