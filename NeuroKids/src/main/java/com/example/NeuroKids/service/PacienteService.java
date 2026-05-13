package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.PacienteRequestDTO;
import com.example.NeuroKids.dto.PacienteResponseDTO;
import com.example.NeuroKids.entity.Paciente;
import com.example.NeuroKids.entity.Responsavel;
import com.example.NeuroKids.entity.Terapeuta;
import com.example.NeuroKids.repository.PacienteRepository;
import com.example.NeuroKids.repository.ResponsavelRepository;
import com.example.NeuroKids.repository.TerapeutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ResponsavelRepository responsavelRepository;
    private final TerapeutaRepository terapeutaRepository;

    // CREATE
    @Transactional
    public PacienteResponseDTO criar(PacienteRequestDTO dto) {
        //regra de negocio
        validarIdade(dto.getDataNascimento());
        //busca responsavel
        Responsavel responsavel = responsavelRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        List<Terapeuta> terapeutas = terapeutaRepository
                .findAllById(dto.getTerapeutasIds());
        //if (!Boolean.TRUE.equals(responsavel.getAtivo())) {
            //throw new BusinessException("O responsável informado está inativo.");}

        Paciente paciente = Paciente.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .diagnostico(dto.getDiagnostico())
                .necessidadesEspecificas(dto.getNecessidadesEspecificas())
                .nivelSuporte(dto.getNivelSuporte())
                .responsavel(responsavel)
                .terapeutas(terapeutas)
                .ativo(true)
                .build();

        return converterParaDTO(
                pacienteRepository.save(paciente)
        );
    }

    @Transactional
    public List<PacienteResponseDTO> listarTodos() {

        return pacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @Transactional
    public PacienteResponseDTO buscarPorId(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return converterParaDTO(paciente);
    }

    // UPDATE
    @Transactional
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setNome(dto.getNome());
        paciente.setDiagnostico(dto.getDiagnostico());
        paciente.setNecessidadesEspecificas(dto.getNecessidadesEspecificas());
        paciente.setNivelSuporte(dto.getNivelSuporte());

        return converterParaDTO(
                pacienteRepository.save(paciente)
        );
    }

    // DELETE
    @Transactional
    public void deletar(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setAtivo(false);

        pacienteRepository.save(paciente);
    }

    private void validarIdade(LocalDate dataNascimento) {

        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        if (idade > 18) {
            throw new RuntimeException(
                    "A plataforma atende apenas menores de idade"
            );
        }
    }

    // MAPPER
    private PacienteResponseDTO converterParaDTO(Paciente paciente) {

        return PacienteResponseDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .dataNascimento(paciente.getDataNascimento())
                .diagnostico(paciente.getDiagnostico())
                .necessidadesEspecificas(
                        paciente.getNecessidadesEspecificas()
                )
                .nivelSuporte(paciente.getNivelSuporte())
                .ativo(paciente.getAtivo())
                .nomeResponsavel(
                        paciente.getResponsavel().getNome()
                )
                .terapeutas(
                        paciente.getTerapeutas()
                                .stream()
                                .map(Terapeuta::getNome)
                                .toList()
                )
                .criadoEm(paciente.getCriadoEm())
                .build();
    }
}