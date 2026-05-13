package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.ProgressoAtividade.StatusProgresso;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressoAtividadeRequestDTO {

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Atividade é obrigatória")
    private Long atividadeId;

    @NotNull(message = "Status é obrigatório")
    private StatusProgresso status;

    @Min(value = 0, message = "Pontuação mínima é 0")
    @Max(value = 100, message = "Pontuação máxima é 100")
    private Integer pontuacao;

    private Integer tentativas;

    private Integer tempoGastoMinutos;

    private String observacoes;
}