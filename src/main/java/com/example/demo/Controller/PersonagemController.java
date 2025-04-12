package com.example.demo.Controller;

import com.example.demo.Exceptions.Exceptions;
import com.example.demo.Model.ItemMagico;
import com.example.demo.Model.Personagem;
import com.example.demo.Service.personagemService;
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
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    public personagemService personagemService;

    @Operation(summary = "Cria um novo personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personagem criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos acima do valor permitido")
    })
    @PostMapping("")
    public ResponseEntity<Personagem> save(@RequestBody Personagem personagem) {
        try {
            return new ResponseEntity<>(personagemService.save(personagem), HttpStatus.CREATED);
        } catch (Exceptions.ValoresMaiores e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atributos acima do valor permitido!");
        }
    }

    @Operation(summary = "Lista todos os personagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("")
    public List<Personagem> findAll() {
        return personagemService.findAll();
    }

    @Operation(summary = "Busca personagem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem encontrado"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    @GetMapping("/{id}")
    public Personagem findById(@PathVariable int id) {
        try {
            return personagemService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado!");
        }
    }

    @Operation(summary = "Atualiza o nome do aventureiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nome do aventureiro atualizado")
    })
    @PatchMapping("/{id}")
    public Personagem atualizarNomeAventureiro(@PathVariable int id, @RequestBody String nome) {
        return personagemService.atualizarNomeAventureiro(id, nome);
    }

    @Operation(summary = "Remove personagem pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem removido com sucesso")
    })
    @DeleteMapping("/{id}")
    public void removerPersonagem(@PathVariable int id) {
        personagemService.deleteById((long) id);
    }

    @Operation(summary = "Adiciona item mágico ao personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item mágico adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Personagem já possui amuleto")
    })
    @PatchMapping("/{id}/adicionarItemMagico/{idItemMagico}")
    public ResponseEntity<Personagem> adicionarItemMagico(@PathVariable int id, @PathVariable int idItemMagico) {
        try {
            return new ResponseEntity<>(personagemService.adicionarItemMagico(id, idItemMagico), HttpStatus.OK);
        } catch (Exceptions.PossuiAmuleto e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personagem já tem amuleto!");
        }
    }

    @Operation(summary = "Lista itens mágicos de um personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens listados com sucesso")
    })
    @GetMapping("/{id}/listarItensMagicos")
    public List<ItemMagico> listarItensMagicos(@PathVariable int id) {
        return personagemService.listarItensMagicos(id);
    }

    @Operation(summary = "Remove item mágico de um personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item mágico removido com sucesso")
    })
    @PatchMapping("/{id}/deletarItemMagico/{idItemMagico}")
    public Personagem removerItemMagico(@PathVariable int id, @PathVariable int idItemMagico) {
        return personagemService.removerItemMagico(id, idItemMagico);
    }

    @Operation(summary = "Busca o amuleto do personagem (se tiver)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amuleto encontrado ou nulo")
    })
    @GetMapping("/{id}/buscarAmuleto")
    public ItemMagico buscarAmuleto(@PathVariable int id) {
        return personagemService.buscarAmuleto(id);
    }
}
