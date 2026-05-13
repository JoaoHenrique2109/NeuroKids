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

    private Integer duracaoMinutos;

    private String faixaEtaria;

    private String indicadaPara;

    private String urlImagem;

    private Boolean ativa;

    private Integer quantidadeProgressos;

    private LocalDateTime criadoEm;
}