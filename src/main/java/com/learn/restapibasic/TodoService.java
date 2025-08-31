package com.learn.restapibasic;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("TodoService")
public class TodoService implements ITodoService{
    @Override
    public String doSomething(){
        return "doing something";
    }
}