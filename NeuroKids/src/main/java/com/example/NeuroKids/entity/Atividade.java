package com.example.NeuroKids.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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


    @Column(nullable = false)
    private Boolean ativa = true;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @JsonIgnore
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
