package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.ProgressoAtividadeRequestDTO;
import com.example.NeuroKids.dto.ProgressoAtividadeResponseDTO;
import com.example.NeuroKids.entity.Atividade;
import com.example.NeuroKids.entity.Paciente;
import com.example.NeuroKids.entity.ProgressoAtividade;
import com.example.NeuroKids.repository.AtividadeRepository;
import com.example.NeuroKids.repository.PacienteRepository;
import com.example.NeuroKids.repository.ProgressoAtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressoAtividadeService {

    private final ProgressoAtividadeRepository progressoRepository;

    private final PacienteRepository pacienteRepository;

    private final AtividadeRepository atividadeRepository;

    // =========================================================
    // CREATE
    // =========================================================

    public ProgressoAtividadeResponseDTO criar(
            ProgressoAtividadeRequestDTO dto
    ) {

        Paciente paciente = pacienteRepository.findById(
                dto.getPacienteId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Paciente não encontrado"
                )
        );

        Atividade atividade = atividadeRepository.findById(
                dto.getAtividadeId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Atividade não encontrada"
                )
        );

        validarPontuacao(dto);

        ProgressoAtividade progresso =
                ProgressoAtividade.builder()
                        .paciente(paciente)
                        .atividade(atividade)
                        .status(dto.getStatus())
                        .pontuacao(dto.getPontuacao())
                        .tentativas(dto.getTentativas())
                        .tempoGastoMinutos(
                                dto.getTempoGastoMinutos()
                        )
                        .observacoes(dto.getObservacoes())
                        .ultimaExecucao(LocalDateTime.now())
                        .concluidoEm(
                                dto.getStatus() ==
                                        ProgressoAtividade
                                                .StatusProgresso
                                                .CONCLUIDO
                                        ? LocalDateTime.now()
                                        : null
                        )
                        .build();

        return converterParaDTO(
                progressoRepository.save(progresso)
        );
    }

    // =========================================================
    // READ ALL
    // =========================================================

    public List<ProgressoAtividadeResponseDTO> listarTodos() {

        return progressoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // =========================================================
    // READ BY ID
    // =========================================================

    public ProgressoAtividadeResponseDTO buscarPorId(
            Long id
    ) {

        ProgressoAtividade progresso =
                progressoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Progresso não encontrado"
                                )
                        );

        return converterParaDTO(progresso);
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public ProgressoAtividadeResponseDTO atualizar(
            Long id,
            ProgressoAtividadeRequestDTO dto
    ) {

        ProgressoAtividade progresso =
                progressoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Progresso não encontrado"
                                )
                        );

        validarPontuacao(dto);

        progresso.setStatus(dto.getStatus());
        progresso.setPontuacao(dto.getPontuacao());
        progresso.setTentativas(dto.getTentativas());
        progresso.setTempoGastoMinutos(
                dto.getTempoGastoMinutos()
        );
        progresso.setObservacoes(dto.getObservacoes());

        progresso.setUltimaExecucao(
                LocalDateTime.now()
        );

        if (dto.getStatus() ==
                ProgressoAtividade.StatusProgresso.CONCLUIDO) {

            progresso.setConcluidoEm(
                    LocalDateTime.now()
            );
        }

        return converterParaDTO(
                progressoRepository.save(progresso)
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deletar(Long id) {

        ProgressoAtividade progresso =
                progressoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Progresso não encontrado"
                                )
                        );

        progressoRepository.delete(progresso);
    }

    // =========================================================
    // REGRAS DE NEGÓCIO
    // =========================================================

    private void validarPontuacao(
            ProgressoAtividadeRequestDTO dto
    ) {

        if (dto.getPontuacao() != null
                && (dto.getPontuacao() < 0
                || dto.getPontuacao() > 100)) {

            throw new RuntimeException(
                    "Pontuação deve estar entre 0 e 100"
            );
        }
    }

    // =========================================================
    // CONVERSOR DTO
    // =========================================================

    private ProgressoAtividadeResponseDTO converterParaDTO(
            ProgressoAtividade progresso
    ) {

        return ProgressoAtividadeResponseDTO.builder()
                .id(progresso.getId())
                .paciente(
                        progresso.getPaciente().getNome()
                )
                .atividade(
                        progresso.getAtividade().getTitulo()
                )
                .status(progresso.getStatus())
                .pontuacao(progresso.getPontuacao())
                .tentativas(progresso.getTentativas())
                .tempoGastoMinutos(
                        progresso.getTempoGastoMinutos()
                )
                .observacoes(progresso.getObservacoes())
                .ultimaExecucao(
                        progresso.getUltimaExecucao()
                )
                .concluidoEm(
                        progresso.getConcluidoEm()
                )
                .criadoEm(progresso.getCriadoEm())
                .build();
    }
}
