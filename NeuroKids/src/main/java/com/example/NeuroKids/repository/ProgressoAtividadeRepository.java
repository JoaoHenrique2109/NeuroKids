package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.ProgressoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgressoAtividadeRepository
        extends JpaRepository<ProgressoAtividade, Long> {

    // QUERY NATIVA 20
    @Query(value = """
            SELECT * FROM progresso_atividades
            WHERE status = :status
            """, nativeQuery = true)
    List<ProgressoAtividade> buscarPorStatus(
            String status
    );

    // QUERY NATIVA 21
    @Query(value = """
            SELECT * FROM progresso_atividades
            WHERE pontuacao >= :pontuacao
            """, nativeQuery = true)
    List<ProgressoAtividade> buscarPorPontuacao(
            Integer pontuacao
    );

    // QUERY NATIVA 22
    @Query(value = """
            SELECT * FROM progresso_atividades
            WHERE paciente_id = :pacienteId
            """, nativeQuery = true)
    List<ProgressoAtividade> buscarPorPaciente(
            Long pacienteId
    );

    // QUERY NATIVA 23
    @Query(value = """
            SELECT * FROM progresso_atividades
            WHERE atividade_id = :atividadeId
            """, nativeQuery = true)
    List<ProgressoAtividade> buscarPorAtividade(
            Long atividadeId
    );
}