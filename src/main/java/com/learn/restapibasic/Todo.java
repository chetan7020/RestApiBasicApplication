package com.learn.restapibasic;

public record Todo(
        int id, boolean completed, String title, int userId
) { }