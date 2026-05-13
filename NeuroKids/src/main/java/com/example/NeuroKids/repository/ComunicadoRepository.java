package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComunicadoRepository
        extends JpaRepository<Comunicado, Long> {

    // QUERY NATIVA 16
    @Query(value = """
            SELECT * FROM comunicados
            WHERE visualizado = false
            """, nativeQuery = true)
    List<Comunicado> buscarNaoVisualizados();

    // QUERY NATIVA 17
    @Query(value = """
            SELECT * FROM comunicados
            WHERE prioridade = :prioridade
            """, nativeQuery = true)
    List<Comunicado> buscarPorPrioridade(
            String prioridade
    );

    // QUERY NATIVA 18
    @Query(value = """
            SELECT * FROM comunicados
            WHERE paciente_id = :pacienteId
            """, nativeQuery = true)
    List<Comunicado> buscarPorPaciente(
            Long pacienteId
    );

    // QUERY NATIVA 19
    @Query(value = """
            SELECT * FROM comunicados
            WHERE tipo = :tipo
            """, nativeQuery = true)
    List<Comunicado> buscarPorTipo(
            String tipo
    );
}
