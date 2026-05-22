package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Paciente.NivelSuporte;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PacienteResponseDTO {

    private Long id;

    private String nome;

    private int idade;

    private LocalDate dataNascimento;

    private String diagnostico;

    private String necessidadesEspecificas;

    private NivelSuporte nivelSuporte;

    private Boolean ativo;

    private String nomeResponsavel;

    private List<String> terapeutas;

    private LocalDateTime criadoEm;
}
