package com.prova.apirestfull.repositories;

import com.prova.apirestfull.entities.Endereco;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    Endereco findById(Long id);

    @NotNull List<Endereco> findAll();
}
