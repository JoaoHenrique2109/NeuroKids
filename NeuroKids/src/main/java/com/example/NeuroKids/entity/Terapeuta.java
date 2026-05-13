package com.example.NeuroKids.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa o profissional de saúde responsável pelo
 * acompanhamento terapêutico das crianças.
 * Exemplos: psicólogo, fonoaudiólogo, terapeuta ocupacional, neuropsicopedagogo.
 */
@Entity
@Table(name = "terapeutas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ============================================================
    // Dados profissionais
    // ============================================================

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    // Número do registro profissional (CRP, CRFa, CREFITO, etc.)
    @Column(name = "registro_profissional", nullable = false, length = 30, unique = true)
    private String registroProfissional;

    // Especialidade: PSICOLOGO, FONOAUDIOLOGO, TERAPEUTA_OCUPACIONAL, NEUROPSICOPEDAGOGO, OUTRO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Especialidade especialidade;

    // Descrição da abordagem terapêutica utilizada
    @Column(name = "abordagem_terapeutica", columnDefinition = "TEXT")
    private String abordagemTerapeutica;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // ============================================================
    // Relacionamentos
    // ============================================================

    // Um terapeuta pode atender vários pacientes
    @ManyToMany(mappedBy = "terapeutas")
    private List<Paciente> pacientes = new ArrayList<>();

    // Um terapeuta pode criar sessões de acompanhamento
    @OneToMany(mappedBy = "terapeuta", cascade = CascadeType.ALL)
    private List<Sessao> sessoes = new ArrayList<>();

    // ============================================================
    // Callbacks JPA
    // ============================================================

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }

    // ============================================================
    // Enum interno
    // ============================================================

    public enum Especialidade {
        PSICOLOGO,
        FONOAUDIOLOGO,
        TERAPEUTA_OCUPACIONAL,
        NEUROPSICOPEDAGOGO,
        PEDAGOGO,
        OUTRO
    }
}