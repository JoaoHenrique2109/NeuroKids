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
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Dados Pessois
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    // Grau de parentesco com a criança: MÃE, PAI, AVO, TIO, TUTOR, OUTRO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Parentesco parentesco;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // Um responsável pode ter múltiplos filhos na plataforma
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Paciente> pacientes = new ArrayList<>();

    public enum Parentesco {
        MAE, PAI, AVO, TIO, TUTOR, OUTRO
    }
}
