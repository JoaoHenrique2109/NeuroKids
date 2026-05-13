package com.example.NeuroKids.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id", nullable = false)
    private Terapeuta terapeuta;

    @Column(name = "data_sessao", nullable = false)
    private LocalDate dataSessao;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;

    // Duração efetiva em minutos (calculada ou informada)
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    // Tipo: PRESENCIAL, ONLINE, DOMICILIAR
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoSessao tipo;

    // Status: AGENDADA, REALIZADA, CANCELADA, REMARCADA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusSessao status;

    // Relato clínico detalhado da sessão
    @Column(name = "relato_clinico", columnDefinition = "TEXT")
    private String relatoClinico;

    // Metas trabalhadas na sessão
    @Column(columnDefinition = "TEXT")
    private String metas;

    // Evolução observada
    @Column(columnDefinition = "TEXT")
    private String evolucao;

    // Plano para próximas sessões
    @Column(name = "proximos_passos", columnDefinition = "TEXT")
    private String proximosPassos;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;


    public enum TipoSessao {
        PRESENCIAL, ONLINE, DOMICILIAR
    }

    public enum StatusSessao {
        AGENDADA, REALIZADA, CANCELADA, REMARCADA
    }
}