package com.unir.operador.configs;

import com.unir.operador.model.response.CreateAccidenteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CreateAccidenteResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        CreateAccidenteResponse result = new CreateAccidenteResponse();
        result.setError(true);
        result.setCode("400");
        ex.getBindingResult().getAllErrors().forEach((error) -> result.setMessage(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}