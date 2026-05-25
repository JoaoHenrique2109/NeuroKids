package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtividadeRepository
        extends JpaRepository<Atividade, Long> {

    @Query(value = """
            SELECT * FROM atividade
            WHERE ativa = true
            """, nativeQuery = true)
    List<Atividade> buscarAtividadesAtivas();

    @Query(value = """
            SELECT * FROM atividade
            WHERE dificuldade = :dificuldade
            """, nativeQuery = true)
    List<Atividade> buscarPorDificuldade(String dificuldade);

    @Query(value = """
            SELECT * FROM atividade
            WHERE tipo = :tipo
            """, nativeQuery = true)
    List<Atividade> buscarPorTipo(String tipo);
}

