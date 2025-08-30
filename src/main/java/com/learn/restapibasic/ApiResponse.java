package com.learn.restapibasic;

public record ApiResponse<T>(
        boolean isSuccess, T data, String errorMessage
){}