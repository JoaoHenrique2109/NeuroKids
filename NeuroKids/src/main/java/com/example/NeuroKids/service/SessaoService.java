package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.SessaoRequestDTO;
import com.example.NeuroKids.dto.SessaoResponseDTO;
import com.example.NeuroKids.entity.Paciente;
import com.example.NeuroKids.entity.Sessao;
import com.example.NeuroKids.entity.Terapeuta;
import com.example.NeuroKids.repository.PacienteRepository;
import com.example.NeuroKids.repository.SessaoRepository;
import com.example.NeuroKids.repository.TerapeutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
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

        Terapeuta terapeuta = terapeutaRepository
                .findById(dto.getTerapeutaId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Terapeuta não encontrado"));
        Sessao sessao = Sessao.builder()
                .paciente(paciente)
                .dataSessao(dto.getDataSessao())
                .horaInicio(dto.getHoraInicio())
                .tipo(dto.getTipo())
                .status(dto.getStatus())
                .relatoClinico(dto.getRelatoClinico())
                .metas(dto.getMetas())
                .evolucao(dto.getEvolucao())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDate.now())
                .terapeuta(terapeuta)
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


        sessao.setDataSessao(dto.getDataSessao());
        sessao.setHoraInicio(dto.getHoraInicio());
        sessao.setTipo(dto.getTipo());
        sessao.setStatus(dto.getStatus());
        sessao.setRelatoClinico(dto.getRelatoClinico());
        sessao.setMetas(dto.getMetas());
        sessao.setEvolucao(dto.getEvolucao());
        sessao.setAtualizadoEm(LocalDate.now());

        return converterParaDTO(sessaoRepository.save(sessao));
    }

    public void deletar(Long id) {

        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        sessaoRepository.delete(sessao);
    }


    private SessaoResponseDTO converterParaDTO(Sessao sessao) {

        return SessaoResponseDTO.builder()
                .id(sessao.getId())
                .paciente(sessao.getPaciente().getNome())
                .terapeutaId(sessao.getTerapeuta().getId())
                .dataSessao(sessao.getDataSessao())
                .horaInicio(sessao.getHoraInicio())
                .tipo(sessao.getTipo())
                .status(sessao.getStatus())
                .relatoClinico(
                        sessao.getRelatoClinico())
                .metas(sessao.getMetas())
                .evolucao(sessao.getEvolucao())
                .criadoEm(sessao.getCriadoEm())
                .build();
    }
}