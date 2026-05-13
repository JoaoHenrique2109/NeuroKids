package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Sessao.StatusSessao;
import com.example.NeuroKids.entity.Sessao.TipoSessao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessaoRequestDTO {

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Terapeuta é obrigatório")
    private Long terapeutaId;

    @NotNull(message = "Data da sessão é obrigatória")
    private LocalDate dataSessao;

    @NotNull(message = "Hora de início é obrigatória")
    private LocalTime horaInicio;

    private LocalTime horaFim;

    @NotNull(message = "Tipo da sessão é obrigatório")
    private TipoSessao tipo;

    @NotNull(message = "Status da sessão é obrigatório")
    private StatusSessao status;

    private String relatoClinico;

    private String metas;

    private String evolucao;

    private String proximosPassos;
}