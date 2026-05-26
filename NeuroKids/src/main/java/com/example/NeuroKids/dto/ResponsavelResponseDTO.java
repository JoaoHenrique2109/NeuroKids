package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Responsavel.Parentesco;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ResponsavelResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Parentesco parentesco;

    private Boolean ativo;


    private List<String> pacientes;

    private LocalDateTime criadoEm;
}
