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
    private Long terapeutaId;
    private LocalDate dataSessao;

    private LocalTime horaInicio;

    private TipoSessao tipo;

    private StatusSessao status;

    private String relatoClinico;

    private String metas;

    private String evolucao;

    private LocalDateTime criadoEm;
    private LocalDate atualizadoEm;
}