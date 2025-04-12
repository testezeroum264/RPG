package com.example.demo.Controller;

import com.example.demo.Exceptions.Exceptions;
import com.example.demo.Model.ItemMagico;
import com.example.demo.Service.itemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/itensmagicos")
public class ItemController {

    @Autowired
    private itemMagicoService itemMagicoService;

    @Operation(summary = "Cria um novo item mágico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item mágico criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos acima do valor permitido")
    })
    @PostMapping("")
    public ResponseEntity<ItemMagico> save(@RequestBody ItemMagico itemMagico) {
        try {
            return new ResponseEntity<>(itemMagicoService.save(itemMagico), HttpStatus.CREATED);
        } catch (Exceptions.ValoresMaiores e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atributos acima do valor permitido!");
        }
    }

    @Operation(summary = "Retorna todos os itens mágicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens mágicos encontrados com sucesso")
    })
    @GetMapping("")
    public List<ItemMagico> findAll() {
        return itemMagicoService.findAll();
    }

    @Operation(summary = "Busca um item mágico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item mágico encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item mágico não encontrado")
    })
    @GetMapping("/{id}")
    public ItemMagico findById(@PathVariable int id) {
        try {
            return itemMagicoService.findById((long) id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item mágico não encontrado");
        }
    }
}
