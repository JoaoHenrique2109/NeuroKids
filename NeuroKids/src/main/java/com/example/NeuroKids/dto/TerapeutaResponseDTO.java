package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Terapeuta.Especialidade;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TerapeutaResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String registroProfissional;

    private Especialidade especialidade;

    private String abordagemTerapeutica;

    private Boolean ativo;

    private List<String> pacientes;

    private LocalDateTime criadoEm;
}
