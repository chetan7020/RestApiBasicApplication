package com.learn.restapibasic;

public record ApiResponse<T>(
        Boolean isSuccess, T data, String errorMessage
){}