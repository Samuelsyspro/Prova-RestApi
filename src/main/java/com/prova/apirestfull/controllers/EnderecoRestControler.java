package com.prova.apirestfull.controllers;

import com.prova.apirestfull.dtos.EnderecoDTO;
import com.prova.apirestfull.services.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoRestControler {

    private final EnderecoService enderecoService;

    public EnderecoRestControler(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> salvarNovoEndereco(@RequestBody EnderecoDTO novoEnderecoDTO) {
        try {
            novoEnderecoDTO = enderecoService.salvarNovoEndereco(novoEnderecoDTO);
            return ResponseEntity.ok(novoEnderecoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> buscarTodosEnderecos() {
        List<EnderecoDTO> listaDeEnderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(listaDeEnderecos);
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Object> buscarCadastroPorId(@PathVariable Long enderecoId) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.buscarEnderecoPorId(enderecoId);
            return ResponseEntity.ok(enderecoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Endereco não encontrado");
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Object> atualizarEndereco(@RequestBody EnderecoDTO enderecoAtualizadoDTO) {
        try {
            enderecoAtualizadoDTO = enderecoService.atualizarEndereco(enderecoAtualizadoDTO);
            return ResponseEntity.ok(enderecoAtualizadoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<Object> deletarEndereco(@PathVariable Long enderecoId) {
        try {
            enderecoService.deletarEndereco(enderecoId);
            return ResponseEntity.ok("Endereco deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
