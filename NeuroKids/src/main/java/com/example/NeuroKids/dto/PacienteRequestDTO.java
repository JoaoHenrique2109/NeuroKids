package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Paciente.NivelSuporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotNull(message = "idade é obrigatório")
    private int idade;

    @NotBlank(message = "Diagnóstico é obrigatório")
    private String diagnostico;

    private String necessidadesEspecificas;

    @NotNull(message = "Nível de suporte é obrigatório")
    private NivelSuporte nivelSuporte;

    @NotNull(message = "Responsável é obrigatório")
    private Long responsavelId;

    private List<Long> terapeutasId;
}