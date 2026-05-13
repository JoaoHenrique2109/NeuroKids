package com.example.NeuroKids.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidade que registra o progresso de um paciente em uma atividade específica.
 * Funciona como tabela associativa entre Paciente e Atividade*/
@Entity
@Table(name = "progresso_atividades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    // Status: NAO_INICIADO, EM_ANDAMENTO, CONCLUIDO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusProgresso status;

    // Pontuação obtida (0 a 100)
    @Column(name = "pontuacao")
    private Integer pontuacao;

    // Número de tentativas realizadas
    @Column(name = "tentativas")
    private Integer tentativas = 0;

    // Tempo real gasto na atividade (em minutos)
    @Column(name = "tempo_gasto_minutos")
    private Integer tempoGastoMinutos;

    // Observações do terapeuta ou responsável sobre a execução
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Data e hora da última execução
    @Column(name = "ultima_execucao")
    private LocalDateTime ultimaExecucao;

    // Data de conclusão definitiva
    @Column(name = "concluido_em")
    private LocalDateTime concluidoEm;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    //salva a data automaticamente
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        ultimaExecucao = LocalDateTime.now();
    }

    public enum StatusProgresso {
        NAO_INICIADO,
        EM_ANDAMENTO,
        CONCLUIDO
    }
}