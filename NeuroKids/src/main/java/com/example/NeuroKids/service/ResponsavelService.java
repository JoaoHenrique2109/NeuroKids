package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.ResponsavelRequestDTO;
import com.example.NeuroKids.dto.ResponsavelResponseDTO;
import com.example.NeuroKids.entity.Responsavel;
import com.example.NeuroKids.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    // CREATE
    public ResponsavelResponseDTO criar(
            ResponsavelRequestDTO dto
    ) {

        validarEmail(dto.getEmail());

        validarCpf(dto.getCpf());

        Responsavel responsavel = Responsavel.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .cpf(dto.getCpf())
                .parentesco(dto.getParentesco())
                .ativo(true)
                .build();

        return converterParaDTO(
                responsavelRepository.save(responsavel)
        );
    }

    // READ ALL
    public List<ResponsavelResponseDTO> listarTodos() {

        return responsavelRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // READ BY ID
    public ResponsavelResponseDTO buscarPorId(Long id) {

        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Responsável não encontrado"
                        )
                );

        return converterParaDTO(responsavel);
    }

    // UPDATE
    public ResponsavelResponseDTO atualizar(
            Long id,
            ResponsavelRequestDTO dto
    ) {

        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Responsável não encontrado"
                        )
                );

        responsavel.setNome(dto.getNome());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setParentesco(dto.getParentesco());

        return converterParaDTO(
                responsavelRepository.save(responsavel)
        );
    }

    // DELETE (SOFT DELETE)
    public void deletar(Long id) {

        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Responsável não encontrado"
                        )
                );

        responsavel.setAtivo(false);

        responsavelRepository.save(responsavel);
    }

    // =====================================================
    // REGRAS DE NEGÓCIO
    // =====================================================

    private void validarEmail(String email) {

        boolean emailExiste = responsavelRepository
                .findByEmail(email)
                .isPresent();

        if (emailExiste) {
            throw new RuntimeException(
                    "Já existe um responsável com este email"
            );
        }
    }

    private void validarCpf(String cpf) {

        boolean cpfExiste = responsavelRepository
                .findByCpf(cpf)
                .isPresent();

        if (cpfExiste) {
            throw new RuntimeException(
                    "CPF já cadastrado"
            );
        }

        if (cpf.length() != 11) {
            throw new RuntimeException(
                    "CPF deve possuir 11 dígitos"
            );
        }
    }

    // =====================================================
    // MAPPER DTO
    // =====================================================

    private ResponsavelResponseDTO converterParaDTO(
            Responsavel responsavel
    ) {

        return ResponsavelResponseDTO.builder()
                .id(responsavel.getId())
                .nome(responsavel.getNome())
                .email(responsavel.getEmail())
                .telefone(responsavel.getTelefone())
                .cpf(responsavel.getCpf())
                .parentesco(responsavel.getParentesco())
                .ativo(responsavel.getAtivo())
                .pacientes(
                        responsavel.getPacientes()
                                .stream()
                                .map(p -> p.getNome())
                                .toList()
                )
                .criadoEm(responsavel.getCriadoEm())
                .build();
    }
}
