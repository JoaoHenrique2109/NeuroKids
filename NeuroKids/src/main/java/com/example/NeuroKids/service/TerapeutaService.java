package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.TerapeutaRequestDTO;
import com.example.NeuroKids.dto.TerapeutaResponseDTO;
import com.example.NeuroKids.entity.Terapeuta;
import com.example.NeuroKids.repository.TerapeutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerapeutaService {

    private final TerapeutaRepository terapeutaRepository;

    public TerapeutaResponseDTO criar(
            TerapeutaRequestDTO dto) {

        validarEmail(dto.getEmail());

        validarRegistro(dto.getRegistroProfissional());

        validarEspecialidade(dto);

        Terapeuta terapeuta = Terapeuta.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .registroProfissional(
                        dto.getRegistroProfissional())
                .especialidade(dto.getEspecialidade())
                .abordagemTerapeutica(
                        dto.getAbordagemTerapeutica())
                .ativo(true)
                .build();

        return converterParaDTO(terapeutaRepository.save(terapeuta));
    }


    public List<TerapeutaResponseDTO> listarTodos() {

        return terapeutaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public TerapeutaResponseDTO buscarPorId(Long id) {

        Terapeuta terapeuta = terapeutaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Terapeuta não encontrado"));
        return converterParaDTO(terapeuta);
    }


    public TerapeutaResponseDTO atualizar(Long id, TerapeutaRequestDTO dto) {

        Terapeuta terapeuta = terapeutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terapeuta não encontrado"));

        terapeuta.setNome(dto.getNome());
        terapeuta.setTelefone(dto.getTelefone());
        terapeuta.setEspecialidade(dto.getEspecialidade());
        terapeuta.setAbordagemTerapeutica(
                dto.getAbordagemTerapeutica()
        );

        return converterParaDTO(terapeutaRepository.save(terapeuta));}


    public void deletar(Long id) {

        Terapeuta terapeuta = terapeutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terapeuta não encontrado"));

        terapeuta.setAtivo(false);

        terapeutaRepository.save(terapeuta);
    }

    private void validarEmail(String email) {

        boolean emailExiste = terapeutaRepository
                .findByEmail(email)
                .isPresent();

        if (emailExiste) {
            throw new RuntimeException("Já existe terapeuta com este email");
        }
    }

    private void validarRegistro(String registro) {

        boolean registroExiste = terapeutaRepository
                .findByRegistroProfissional(registro)
                .isPresent();

        if (registroExiste) {
            throw new RuntimeException("Registro profissional já cadastrado");
        }
    }

    private void validarEspecialidade(TerapeutaRequestDTO dto) {

        if (dto.getEspecialidade() ==
                Terapeuta.Especialidade.OUTRO
                &&
                (dto.getAbordagemTerapeutica() == null
                        || dto.getAbordagemTerapeutica().isBlank())) {

            throw new RuntimeException("Descreva a abordagem terapêutica");
        }
    }

    private TerapeutaResponseDTO converterParaDTO(Terapeuta terapeuta) {

        return TerapeutaResponseDTO.builder()
                .id(terapeuta.getId())
                .nome(terapeuta.getNome())
                .email(terapeuta.getEmail())
                .telefone(terapeuta.getTelefone())
                .registroProfissional(terapeuta.getRegistroProfissional())
                .especialidade(terapeuta.getEspecialidade())
                .abordagemTerapeutica(terapeuta.getAbordagemTerapeutica())
                .ativo(terapeuta.getAtivo())
                .pacientes(terapeuta.getPacientes()
                                .stream()
                                .map(p -> p.getNome())
                                .toList())
                .criadoEm(terapeuta.getCriadoEm())
                .build();
    }
}