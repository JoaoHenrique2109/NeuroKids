package com.example.NeuroKids.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    // Tipo: RECADO, ORIENTACAO, ALERTA, EVENTO, RELATORIO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoComunicado tipo;

    // Prioridade: BAIXA, MEDIA, ALTA, URGENTE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Prioridade prioridade;

    // Origem: ESCOLA, TERAPEUTA, RESPONSAVEL
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Origem origem;

    // Se o comunicado foi visualizado pelo destinatário
    @Column(name = "visualizado")
    private Boolean visualizado = false;

    // Data/hora de visualização
    @Column(name = "visualizado_em")
    private LocalDateTime visualizadoEm;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;


    // Comunicado pertence a um paciente específico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    // Comunicado pode ser de um terapeuta específico (opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id")
    private Terapeuta terapeuta;


    public enum TipoComunicado {
        RECADO, ORIENTACAO, ALERTA, EVENTO, RELATORIO
    }

    public enum Prioridade {
        BAIXA, MEDIA, ALTA, URGENTE
    }

    public enum Origem {
        ESCOLA, TERAPEUTA, RESPONSAVEL
    }
}
