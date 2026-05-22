package com.example.NeuroKids.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(name = "registro_profissional", nullable = false, length = 30, unique = true)
    private String registroProfissional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Especialidade especialidade;

    @Column(name = "abordagem_terapeutica", columnDefinition = "TEXT")
    private String abordagemTerapeutica;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "terapeutas")
    private List<Paciente> pacientes = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "terapeuta", cascade = CascadeType.ALL)
    private List<Sessao> sessoes = new ArrayList<>();

    public enum Especialidade {
        PSICOLOGO,
        FONOAUDIOLOGO,
        TERAPEUTA_OCUPACIONAL,
        NEUROPSICOPEDAGOGO,
        PEDAGOGO,
        OUTRO
    }
}