package it.unicam.coloni.hackhub.shared.infrastructure.web;

import it.unicam.coloni.hackhub.context.identity.infrastructure.web.Messages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    ApiResponseFactory responseFactory;


    @Autowired
    public ApiExceptionHandler(ApiResponseFactory factory){
        this.responseFactory = factory;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request)
    {
        HttpStatus internalStatus = HttpStatus.BAD_REQUEST;
        ApiResponse<List<String>> response = responseFactory.createErrorResponse(
            ex, null, internalStatus
        );
        return new ResponseEntity<>(response, internalStatus);
    }






    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDatabase(DataIntegrityViolationException ex){
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>( responseFactory.createErrorResponse(
                ex,
                null,
                status

        ),status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        ex.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>( responseFactory.createErrorResponse(ex, null, status), status);
    }



}
