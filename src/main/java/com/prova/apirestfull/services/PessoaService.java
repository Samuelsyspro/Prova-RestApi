package com.prova.apirestfull.services;

import com.prova.apirestfull.dtos.PessoaDTO;
import com.prova.apirestfull.entities.Pessoa;
import com.prova.apirestfull.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaDTO salvarNovoCadastro(PessoaDTO pessoaDTO) {

        Pessoa pessoasQueVaiSerGravado;

        if (pessoaDTO.getId() != null) {
            Pessoa verificaSeExistePessoas = pessoaRepository.findById(pessoaDTO.getId());
            if (verificaSeExistePessoas != null)
                throw new RuntimeException("Cadastro já existente");
        }
        pessoasQueVaiSerGravado = new Pessoa();
        return this.registrarCadastro(pessoasQueVaiSerGravado, pessoaDTO);
    }

    public List<PessoaDTO> listarTodos() {
        List<PessoaDTO> listaCadastrosDTO = new ArrayList<>();
        List<Pessoa> pessoas = pessoaRepository.findAll();
        pessoas.forEach(pessoa -> {
            PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
            listaCadastrosDTO.add(pessoaDTO);
        });
        return listaCadastrosDTO;
    }

    public PessoaDTO buscarCadastroPorId(Long cadastroId) {
        Pessoa pessoa = pessoaRepository.findById(cadastroId);
        if (pessoa == null)
            throw new RuntimeException("Cadastro não encontrado");
        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO atualizarCadastro(PessoaDTO pessoaDTO) {

        if (pessoaDTO.getId() == null)
            throw new RuntimeException("Cadastro não encontrado");

        Pessoa pessoasQueVaiSerAtualizado = pessoaRepository.findById(pessoaDTO.getId());

        if (pessoasQueVaiSerAtualizado == null) {
            throw new RuntimeException("Cadastro não encontrado");
        }
        pessoasQueVaiSerAtualizado.setDataAtualizacao(LocalDateTime.now());
        return this.registrarCadastro(pessoasQueVaiSerAtualizado, pessoaDTO);
    }

    public void deletarCadastro(Long cadastroId) {
        Pessoa pessoasQueVaiSerDeletado = pessoaRepository.findById(cadastroId);
        if (pessoasQueVaiSerDeletado == null) {
            throw new RuntimeException("Cadastro não encontrado");
        }
        pessoasQueVaiSerDeletado.setDataAtualizacao(LocalDateTime.now());
        this.pessoaRepository.delete(pessoasQueVaiSerDeletado);
    }

    private PessoaDTO registrarCadastro(Pessoa pessoasQueVaiSerGravado, PessoaDTO pessoaDTO) {
        pessoasQueVaiSerGravado.setNome(pessoaDTO.getNome());
        pessoasQueVaiSerGravado.setSobrenome(pessoaDTO.getSobrenome());
        pessoasQueVaiSerGravado.setCpf(pessoaDTO.getCpf());
        pessoasQueVaiSerGravado.setRg(pessoaDTO.getRg());
        pessoaRepository.save(pessoasQueVaiSerGravado);
        pessoaDTO.setId(pessoasQueVaiSerGravado.getId());

        return pessoaDTO;
    }

}
