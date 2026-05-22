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
import java.util.ArrayList;
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

        validarIdade(dto.getDataNascimento());

        Responsavel responsavel;

        // Se não vier responsável, cria automático
        if(dto.getResponsavelId() == null){

            responsavel = Responsavel.builder()
                    .nome("Responsável Padrão")
                    .ativo(true)
                    .build();

            responsavel = responsavelRepository.save(responsavel);

        }else{

            responsavel = responsavelRepository
                    .findById(dto.getResponsavelId())
                    .orElseGet(() -> {

                        Responsavel novo = Responsavel.builder()
                                .nome("Responsável Automático")
                                .ativo(true)
                                .build();

                        return responsavelRepository.save(novo);

                    });

        }

        List<Terapeuta> terapeutas = new ArrayList<>();

        if(dto.getTerapeutasId() != null
                && !dto.getTerapeutasId().isEmpty()){

            terapeutas = terapeutaRepository.findAllById(
                    dto.getTerapeutasId()
            );

            System.out.println(
                    "TERAPEUTAS ENCONTRADOS: "
                            + terapeutas.size()
            );
        }
        Paciente paciente = Paciente.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .idade(dto.getIdade())
                .diagnostico(dto.getDiagnostico())
                .necessidadesEspecificas(dto.getNecessidadesEspecificas())
                .nivelSuporte(dto.getNivelSuporte())
                .responsavel(responsavel)
                .terapeutas(terapeutas)
                .ativo(true)
                .build();

        Paciente salvo = pacienteRepository.save(paciente);

        return converterParaDTO(salvo);
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
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setIdade(dto.getIdade());
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