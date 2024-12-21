package com.unir.buscador.controller;

import com.unir.buscador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/categories")
@Slf4j
@Tag(name = "Ejemplo Controller", description = "Microservicio")
public class EjemploController {

    @GetMapping()
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
    public ResponseEntity<Integer> getCategorys() {

        Integer response = 1;

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
