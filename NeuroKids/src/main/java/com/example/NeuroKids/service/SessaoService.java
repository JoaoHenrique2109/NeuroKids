package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.SessaoRequestDTO;
import com.example.NeuroKids.dto.SessaoResponseDTO;
import com.example.NeuroKids.entity.Paciente;
import com.example.NeuroKids.entity.Sessao;
import com.example.NeuroKids.entity.Terapeuta;
import com.example.NeuroKids.repository.PacienteRepository;
import com.example.NeuroKids.repository.SessaoRepository;
import com.example.NeuroKids.repository.TerapeutaRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    private final PacienteRepository pacienteRepository;

    private final TerapeutaRepository terapeutaRepository;


    public SessaoResponseDTO criar(SessaoRequestDTO dto) {

        Paciente paciente = pacienteRepository.findById(
                dto.getPacienteId()
        ).orElseThrow(() ->
                new RuntimeException("Paciente não encontrado"));

        Terapeuta terapeuta = terapeutaRepository.findById(
                dto.getTerapeutaId()).orElseThrow(() -> new RuntimeException("Terapeuta não encontrado"));

        validarHorario(dto);

        Sessao sessao = Sessao.builder()
                .paciente(paciente)
                .terapeuta(terapeuta)
                .dataSessao(dto.getDataSessao())
                .horaInicio(dto.getHoraInicio())
                .horaFim(dto.getHoraFim())
                .tipo(dto.getTipo())
                .status(dto.getStatus())
                .relatoClinico(dto.getRelatoClinico())
                .metas(dto.getMetas())
                .evolucao(dto.getEvolucao())
                .proximosPassos(dto.getProximosPassos())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .duracaoMinutos(
                        calcularDuracao(
                                dto.getHoraInicio(),
                                dto.getHoraFim()))
                .build();

        return converterParaDTO(sessaoRepository.save(sessao));
    }


    public List<SessaoResponseDTO> listarTodos() {

        return sessaoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public SessaoResponseDTO buscarPorId(Long id) {

        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        return converterParaDTO(sessao);
    }

    public SessaoResponseDTO atualizar(Long id, SessaoRequestDTO dto) {

        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        validarHorario(dto);

        sessao.setDataSessao(dto.getDataSessao());
        sessao.setHoraInicio(dto.getHoraInicio());
        sessao.setHoraFim(dto.getHoraFim());
        sessao.setTipo(dto.getTipo());
        sessao.setStatus(dto.getStatus());
        sessao.setRelatoClinico(dto.getRelatoClinico());
        sessao.setMetas(dto.getMetas());
        sessao.setEvolucao(dto.getEvolucao());
        sessao.setProximosPassos(dto.getProximosPassos());
        sessao.setAtualizadoEm(LocalDateTime.now());
        sessao.setDuracaoMinutos(
                calcularDuracao(dto.getHoraInicio(),
                        dto.getHoraFim()));

        return converterParaDTO(sessaoRepository.save(sessao));
    }

    public void deletar(Long id) {

        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        sessaoRepository.delete(sessao);
    }

    private void validarHorario(SessaoRequestDTO dto) {

        if (dto.getHoraFim() != null &&
                dto.getHoraFim().isBefore(dto.getHoraInicio())) {

            throw new RuntimeException("Hora final não pode ser menor que hora inicial");
        }
    }

    private Integer calcularDuracao(java.time.LocalTime inicio, java.time.LocalTime fim) {

        if (inicio == null || fim == null) {
            return 0;
        }

        return (int) Duration.between(inicio, fim)
                .toMinutes();
    }

    private SessaoResponseDTO converterParaDTO(Sessao sessao) {

        return SessaoResponseDTO.builder()
                .id(sessao.getId())
                .paciente(sessao.getPaciente().getNome())
                .terapeuta(sessao.getTerapeuta().getNome())
                .dataSessao(sessao.getDataSessao())
                .horaInicio(sessao.getHoraInicio())
                .horaFim(sessao.getHoraFim())
                .duracaoMinutos(
                        sessao.getDuracaoMinutos())
                .tipo(sessao.getTipo())
                .status(sessao.getStatus())
                .relatoClinico(
                        sessao.getRelatoClinico())
                .metas(sessao.getMetas())
                .evolucao(sessao.getEvolucao())
                .proximosPassos(sessao.getProximosPassos())
                .criadoEm(sessao.getCriadoEm())
                .build();
    }

}