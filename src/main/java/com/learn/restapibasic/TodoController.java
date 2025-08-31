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

    @GetMapping("/{todoId}")
    public ResponseEntity<ApiResponse<Todo>> getTodo(@PathVariable int todoId){
        for(Todo todo : todoList){
            if(todo.getId() == todoId){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todo, null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "todo with id : " + todoId + " not found"));
    }

    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED) - can be used to set status code in case of succeses
    public ResponseEntity<ApiResponse<Todo>> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, newTodo, null));
    }


    // patch
    @PatchMapping("/")
    public ResponseEntity<ApiResponse<Todo>> patchTodo(@RequestBody Todo updateTodo){
        for(Todo todo : todoList){
            if(todo.getId().equals(updateTodo.getId())){
                if(todo.getCompleted() != null){
                    todo.setCompleted(updateTodo.getCompleted());
                }

                if(todo.getTitle() != null){
                    todo.setTitle(updateTodo.getTitle());
                }

                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todo, null));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "todo with id " + updateTodo.getId() + " not fopund"));
    }
    
    //put
    @PutMapping("/")
    public ResponseEntity<ApiResponse<Todo>> putTodo(@RequestBody Todo updateTodo){
        for(Todo todo : todoList){
            if(todo.getId().equals(updateTodo.getId())){
                todo = updateTodo;
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todo, null));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "todo with id " + updateTodo.getId() + " not fopund"));
    }
    
    // delete
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse<Todo>> deleteTodo(@RequestBody Todo updateTodo){
        for(Todo todo : todoList){
            if(todo.getId().equals(updateTodo.getId())){
                todoList.remove(todo);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, todo, null));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "todo with id " + updateTodo.getId() + " not fopund"));
    }
}
