package com.prova.apirestfull.services;

import com.prova.apirestfull.dtos.EnderecoDTO;
import com.prova.apirestfull.entities.Endereco;
import com.prova.apirestfull.entities.Pessoa;
import com.prova.apirestfull.repositories.EnderecoRepository;
import com.prova.apirestfull.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class EnderecoService {
    final
    PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public EnderecoDTO salvarNovoEndereco(EnderecoDTO enderecoDTO) {
        Endereco enderecoQueVaiSerGravado;
        if (enderecoDTO.getId() != null) {
            Endereco verificaSeExisteEndereco = enderecoRepository.findById(enderecoDTO.getId());
            if (verificaSeExisteEndereco != null)
                throw new RuntimeException("Endereço já existente");
        }
        enderecoQueVaiSerGravado = new Endereco();
        return this.registrarEndereco(enderecoQueVaiSerGravado, enderecoDTO);
    }

    public List<EnderecoDTO> listarTodos() {
        List<EnderecoDTO> listaEnderecosDTO = new ArrayList<>();
        List<Endereco> enderecos = enderecoRepository.findAll();
        enderecos.forEach(endereco -> {
            EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
            listaEnderecosDTO.add(enderecoDTO);
        });
        return listaEnderecosDTO;
    }

    public EnderecoDTO buscarEnderecoPorId(Long enderecoId) {
        Endereco endereco = enderecoRepository.findById(enderecoId);
        if (endereco == null)
            throw new RuntimeException("Endereço não encontrado");
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        return enderecoDTO;
    }

    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO) {

        if (enderecoDTO.getId() == null)
            throw new RuntimeException("Endereço não encontrado");

        Endereco enderecoQueVaiSerAtualizado = enderecoRepository.findById(enderecoDTO.getId());

        if (enderecoQueVaiSerAtualizado == null) {
            throw new RuntimeException("Endereço não encontrado");
        }
        enderecoQueVaiSerAtualizado.setDataAtualizacao(LocalDateTime.now());
        return this.registrarEndereco(enderecoQueVaiSerAtualizado, enderecoDTO);
    }

    public void deletarEndereco(Long enderecoId) {
        Endereco enderecoQueVaiSerDeletado = enderecoRepository.findById(enderecoId);
        if (enderecoQueVaiSerDeletado == null) {
            throw new RuntimeException("Endereço não encontrado");
        }
        enderecoQueVaiSerDeletado.setDataAtualizacao(LocalDateTime.now());
        this.enderecoRepository.delete(enderecoQueVaiSerDeletado);
    }

    private EnderecoDTO registrarEndereco(Endereco enderecoQueVaiSerGravado, EnderecoDTO enderecoDTO) {
        Pessoa pessoa;
        try {
            pessoa=pessoaRepository.findById(enderecoDTO.getPessoaId());
            if (pessoa == null)
                throw new RuntimeException("Pessoa não encontrada");
            enderecoQueVaiSerGravado.setPessoa(pessoa);
        } catch (Exception e) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        enderecoQueVaiSerGravado.setCep(enderecoDTO.getCep());
        enderecoQueVaiSerGravado.setLogradouro(enderecoDTO.getLogradouro());
        enderecoQueVaiSerGravado.setNumero(enderecoDTO.getNumero());
        enderecoQueVaiSerGravado.setComplemento(enderecoDTO.getComplemento());
        enderecoRepository.save(enderecoQueVaiSerGravado);
        enderecoDTO.setId(enderecoQueVaiSerGravado.getId());
        return enderecoDTO;

    }

}
