package it.unicam.coloni.hackhub.shared.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseFactory {

    public  <T> ApiResponse<T> createSuccessResponse(String message, T data){
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .data(data)
                .build();
    }


    public <T> ApiResponse<T> createErrorResponse(Exception exception, T data, HttpStatus status){
        return ApiResponse.<T>builder()
                .message(exception.getLocalizedMessage())
                .data(data)
                .success(false)
                .code(status.value())
                .build();
    }
}
