package com.entrega.calculator.controller;

import com.entrega.calculator.model.pojo.Operation;
import com.entrega.calculator.service.OperationsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.entrega.calculator.model.request.CreateOperationRequest;


import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Operations Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en una base de datos en memoria.")
public class OperationsController {

    private final OperationsService service;

    @GetMapping("/operations")
    @io.swagger.v3.oas.annotations.Operation(
            operationId = "Obtener las operaciones",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todas las operaciones almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Operation.class)))
    public ResponseEntity<List<Operation>> getOperations() {

        List<Operation> operations = service.getOperations();

        if (operations != null) {
            return ResponseEntity.ok(operations);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/operations/{operationsId}")
    @io.swagger.v3.oas.annotations.Operation(
            operationId = "Obtener una operación concreta",
            description = "Operacion de lectura",
            summary = "Se devuelve una operación a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Operation.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Operation> getOperationById(@PathVariable String operationsId) {

        log.info("Request received for operation {}", operationsId);
        Operation operation = service.getOperationById(operationsId);

        if (operation != null) {
            return ResponseEntity.ok(operation);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/operations")
    @io.swagger.v3.oas.annotations.Operation(
            operationId = "Insertar una operación",
            description = "Operacion de escritura",
            summary = "Se crea una operación a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la operación a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOperationRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Operation.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos introduccidos incorrectos.")
    public ResponseEntity<Operation> addOperation(@RequestBody CreateOperationRequest request) {

        Operation createdOperation = service.addOperation(request);

        if (createdOperation != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOperation);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
