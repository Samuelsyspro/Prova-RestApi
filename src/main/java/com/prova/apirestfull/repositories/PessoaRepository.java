package com.prova.apirestfull.repositories;

import com.sun.istack.NotNull;
import com.prova.apirestfull.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {

    Pessoa findById(Long id);

    Pessoa findByNome(String nome);

    @NotNull List<Pessoa> findAll();
}

