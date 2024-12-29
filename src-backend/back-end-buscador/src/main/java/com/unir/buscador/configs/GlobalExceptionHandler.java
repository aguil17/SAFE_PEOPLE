package com.unir.buscador.configs;

import com.unir.buscador.model.response.GetAllIncidentesResponse;
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
    public ResponseEntity<GetAllIncidentesResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        GetAllIncidentesResponse result = new GetAllIncidentesResponse();
        result.setError(true);
        result.setCode("400");
        ex.getBindingResult().getAllErrors().forEach((error) -> result.setMessage(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
