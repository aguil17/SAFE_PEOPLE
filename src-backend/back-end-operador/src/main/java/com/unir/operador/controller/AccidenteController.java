package com.unir.operador.controller;

import com.unir.operador.model.request.CreateAccidenteRequest;
import com.unir.operador.model.response.CreateAccidenteResponse;
import com.unir.operador.service.AccidenteService;
import com.unir.operador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/accidente")
//@RequiredArgsConstructor
@Slf4j
@Tag(name = "Accidente Controller", description = "Gestión del Accidente")
public class AccidenteController {

    private final AccidenteService accidenteService;

    public AccidenteController(AccidenteService accidenteService) {
        this.accidenteService = accidenteService;
    }

    @GetMapping
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
    public ResponseEntity<CreateAccidenteResponse> getCategorys() {

        var response = new CreateAccidenteResponse();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(
            operationId = "accidente-post",
            description = "Operación de registro de accidente",
            summary = "Se registra un accidente en la base de datos.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAccidenteResponse.class)))
    public ResponseEntity<CreateAccidenteResponse> crearAccidente(@Valid @RequestBody CreateAccidenteRequest request)
    {
        var serviceResult = accidenteService.crearAccidente(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }

}