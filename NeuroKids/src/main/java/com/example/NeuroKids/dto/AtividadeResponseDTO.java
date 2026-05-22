package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Atividade.Dificuldade;
import com.example.NeuroKids.entity.Atividade.TipoAtividade;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AtividadeResponseDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private String instrucoes;

    private TipoAtividade tipo;

    private Dificuldade dificuldade;

    private Integer quantidadeProgressos;

    private LocalDateTime criadoEm;
}