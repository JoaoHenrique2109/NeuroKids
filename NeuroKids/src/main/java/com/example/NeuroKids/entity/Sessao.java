package com.example.NeuroKids.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Terapeuta terapeuta;

    @Column(name = "data_sessao", nullable = false)
    private LocalDate dataSessao;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;


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


    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDate atualizadoEm;


    public enum TipoSessao {
        PRESENCIAL, ONLINE, DOMICILIAR
    }

    public enum StatusSessao {
        AGENDADA, REALIZADA, CANCELADA, REMARCADA
    }
}