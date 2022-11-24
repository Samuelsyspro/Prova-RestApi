package com.prova.apirestfull.dtos;

import com.prova.apirestfull.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;

    public PessoaDTO(Pessoa pessoas) {
        super();
        if (pessoas.getId() != null) {
            this.id = pessoas.getId();
            this.nome = pessoas.getNome();
            this.sobrenome = pessoas.getSobrenome();
            this.cpf = pessoas.getCpf();
            this.rg = pessoas.getRg();
        }
    }
}
