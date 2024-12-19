package com.unir.operador.controller;

import com.unir.operador.model.response.CreateModelResult;
import com.unir.operador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;


@RestController
//@RequiredArgsConstructor
@Slf4j
@Tag(name = "Ejemplo Controller", description = "Microservicio")
public class EjemploController {

    @GetMapping("/categories")
    @Operation(
            operationId = "categories-get",
            description = "Operacion de lectura para devolver las categorias",
            summary = "Se devuelve una lista de las categorias almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = ResponseMessage.EJEMPLO_NOT_FOUND)
    public ResponseEntity<CreateModelResult> getCategorys() {

        var response = new CreateModelResult();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
