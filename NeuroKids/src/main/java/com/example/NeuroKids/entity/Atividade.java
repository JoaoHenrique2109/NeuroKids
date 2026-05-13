package com.example.NeuroKids.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    // Instruções passo a passo para realizar a atividade
    @Column(columnDefinition = "TEXT")
    private String instrucoes;

    //tipo de atividade
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoAtividade tipo;

    // Nível de dificuldade
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Dificuldade dificuldade;

    // Duração
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    // Faixa etária recomendada
    @Column(name = "faixa_etaria", length = 30)
    private String faixaEtaria;

    // Diagnósticos para os quais a atividade é especialmente indicada
    @Column(name = "indicada_para", length = 200)
    private String indicadaPara;

    // URL da imagem ou thumbnail da atividade
    @Column(name = "url_imagem", length = 500)
    private String urlImagem;

    @Column(nullable = false)
    private Boolean ativa = true;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;


    // Registros de progresso dos pacientes nesta atividade
    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressoAtividade> progressos = new ArrayList<>();


    public enum TipoAtividade {
        JOGO, EXERCICIO, LEITURA, ARTE, MUSICA, MOTOR, COGNITIVO
    }

    public enum Dificuldade {
        FACIL, MEDIO, DIFICIL
    }
}
