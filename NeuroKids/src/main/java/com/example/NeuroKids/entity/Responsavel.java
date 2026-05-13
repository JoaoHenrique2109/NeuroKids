package com.example.NeuroKids.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    // Um responsável pode ter várias crianças
    @OneToMany(mappedBy = "responsavel")
    private List<Crianca> criancas;


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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Crianca> getCriancas() {
        return criancas;
    }

    public void setCriancas(List<Crianca> criancas) {
        this.criancas = criancas;
    }
}
