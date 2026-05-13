package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Comunicado.Origem;
import com.example.NeuroKids.entity.Comunicado.Prioridade;
import com.example.NeuroKids.entity.Comunicado.TipoComunicado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComunicadoRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Conteúdo é obrigatório")
    private String conteudo;

    @NotNull(message = "Tipo do comunicado é obrigatório")
    private TipoComunicado tipo;

    @NotNull(message = "Prioridade é obrigatória")
    private Prioridade prioridade;

    @NotNull(message = "Origem é obrigatória")
    private Origem origem;

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

    private Long terapeutaId;
}