package com.example.NeuroKids.dto;

import com.example.NeuroKids.entity.Comunicado.Origem;
import com.example.NeuroKids.entity.Comunicado.Prioridade;
import com.example.NeuroKids.entity.Comunicado.TipoComunicado;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ComunicadoResponseDTO {

    private Long id;

    private String titulo;

    private String conteudo;

    private TipoComunicado tipo;

    private Prioridade prioridade;

    private Origem origem;

    private Boolean visualizado;

    private LocalDateTime visualizadoEm;

    private String paciente;

    private String terapeuta;

    private LocalDateTime criadoEm;
}
