package com.example.NeuroKids.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    // Diagnóstico principal: TEA, TDAH, Dislexia, TDC, etc.
    @Column(nullable = false, length = 100)
    private String diagnostico;

    // Informações adicionais sobre necessidades específicas
    @Column(name = "necessidades_especificas", columnDefinition = "TEXT")
    private String necessidadesEspecificas;

    // Nível de suporte
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_suporte", nullable = false)
    private NivelSuporte nivelSuporte;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;


    // Cada paciente tem um responsável
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Responsavel responsavel;

    // Paciente pode ter vários terapeutas
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "paciente_terapeuta",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "terapeuta_id"))
    @JsonIgnore
    private List<Terapeuta> terapeutas = new ArrayList<>();

    // Histórico de atividades realizadas
    @JsonIgnore
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressoAtividade> progressos = new ArrayList<>();

    public enum NivelSuporte {NIVEL_1, NIVEL_2, NIVEL_3}
}