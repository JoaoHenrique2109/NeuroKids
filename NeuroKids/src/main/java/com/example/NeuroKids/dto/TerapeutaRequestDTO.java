package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Terapeuta.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TerapeutaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Registro profissional é obrigatório")
    private String registroProfissional;

    @NotNull(message = "Especialidade é obrigatória")
    private Especialidade especialidade;

    private String abordagemTerapeutica;
}