package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.ProgressoAtividade.StatusProgresso;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProgressoAtividadeResponseDTO {

    private Long id;

    private String paciente;

    private String atividade;

    private StatusProgresso status;

    private Integer pontuacao;

    private Integer tentativas;

    private Integer tempoGastoMinutos;

    private String observacoes;

    private LocalDateTime ultimaExecucao;

    private LocalDateTime concluidoEm;

    private LocalDateTime criadoEm;
}
