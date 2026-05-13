package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Sessao.StatusSessao;
import com.example.NeuroKids.entity.Sessao.TipoSessao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class SessaoResponseDTO {

    private Long id;

    private String paciente;

    private String terapeuta;

    private LocalDate dataSessao;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private Integer duracaoMinutos;

    private TipoSessao tipo;

    private StatusSessao status;

    private String relatoClinico;

    private String metas;

    private String evolucao;

    private String proximosPassos;

    private LocalDateTime criadoEm;
}