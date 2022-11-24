package com.prova.apirestfull.controllers;

import com.prova.apirestfull.dtos.PessoaDTO;
import com.prova.apirestfull.services.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoasRestController {

    private final PessoaService pessoaService;

    public PessoasRestController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> salvarNovoCadastro(@RequestBody PessoaDTO novoPessoaDTO) {
        try {
            novoPessoaDTO = pessoaService.salvarNovoCadastro(novoPessoaDTO);
            return ResponseEntity.ok(novoPessoaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> buscarTodosCadastros() {
        List<PessoaDTO> listaDeCadastros = pessoaService.listarTodos();
        return ResponseEntity.ok(listaDeCadastros);
    }

    @GetMapping("/{cadastroId}")
    public ResponseEntity<Object> buscarCadastroPorId(@PathVariable Long cadastroId) {
        try {
            PessoaDTO pessoaDTO = pessoaService.buscarCadastroPorId(cadastroId);
            return ResponseEntity.ok(pessoaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cadastro não encontrado");
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Object> atualizarCadastro(@RequestBody PessoaDTO cadastroAtualizadoDTO) {
        try {
            cadastroAtualizadoDTO = pessoaService.atualizarCadastro(cadastroAtualizadoDTO);
            return ResponseEntity.ok(cadastroAtualizadoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{cadastroId}")
    public ResponseEntity<Object> deletarCadastro(@PathVariable Long cadastroId) {
        try {
            pessoaService.deletarCadastro(cadastroId);
            return ResponseEntity.ok("Cadastro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
