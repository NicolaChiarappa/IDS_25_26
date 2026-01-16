package it.unicam.coloni.hackhub.shared.infrastructure.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    boolean success;
    String message;
    T data;
}
