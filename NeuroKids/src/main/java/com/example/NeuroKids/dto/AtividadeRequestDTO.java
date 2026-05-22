package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Atividade.Dificuldade;
import com.example.NeuroKids.entity.Atividade.TipoAtividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtividadeRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private String instrucoes;

    @NotNull(message = "Tipo da atividade é obrigatório")
    private TipoAtividade tipo;

    @NotNull(message = "Dificuldade é obrigatória")
    private Dificuldade dificuldade;

}