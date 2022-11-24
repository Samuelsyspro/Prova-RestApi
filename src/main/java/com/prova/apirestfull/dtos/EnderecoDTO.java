package com.prova.apirestfull.dtos;

import com.prova.apirestfull.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

        private Long id;
        private String cep;
        private String logradouro;
        private String numero;
        private String complemento;
        private Long pessoaId;
        private String nome;
        private String sobrenome;
        private String cpf;
        private String rg;


        public EnderecoDTO(Endereco endereco) {
            super();
            if (endereco.getId() != null) {
                this.id = endereco.getId();
                this.cep = endereco.getCep();
                this.logradouro = endereco.getLogradouro();
                this.numero = endereco.getNumero();
                this.complemento = endereco.getComplemento();
                this.pessoaId = endereco.getPessoa().getId();
                this.nome = endereco.getPessoa().getNome();
                this.sobrenome = endereco.getPessoa().getSobrenome();
                this.cpf = endereco.getPessoa().getCpf();
                this.rg = endereco.getPessoa().getRg();
            }
        }
}
