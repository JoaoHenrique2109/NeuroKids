package com.example.NeuroKids.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

// Define que essa classe será uma tabela no banco
@Entity
public class Crianca {

    // Define chave primária
    @Id
// incremento
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

        // Nome da criança
        private String nome;

        // Data de nascimento
        private LocalDate dataNascimento;

        // Diagnóstico da criança
        private String diagnostico;

        // Relacionamento ManyToOne
        // Muitas crianças podem ter um responsável
        @ManyToOne
        @JoinColumn(name = "responsavel_id")
        private Responsavel responsavel;


        // GETTERS E SETTERS

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getDiagnostico() {
            return diagnostico;
        }

        public void setDiagnostico(String diagnostico) {
            this.diagnostico = diagnostico;
        }

        public Responsavel getResponsavel() {
            return responsavel;
        }

        public void setResponsavel(Responsavel responsavel) {
            this.responsavel = responsavel;
        }
}