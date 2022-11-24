package com.prova.apirestfull.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco extends EntidadeAbstrata{
    @Column(nullable = false, length = 10)
    private String cep;
    @Column(nullable = false, length = 50)
    private String logradouro;
    @Column(nullable = false, length = 8)
    private String numero;
    @Column(nullable = false, length = 50)
    private String complemento;

    @ManyToOne
    @JoinColumn(nullable = false, name = "pessoaid")
    private Pessoa pessoa;
}