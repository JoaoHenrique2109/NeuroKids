package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    // QUERY NATIVA 9
    @Query(value = """
            SELECT * FROM sessao
            WHERE status = :status
            """, nativeQuery = true)
    List<Sessao> buscarPorStatus(String status);

    // QUERY NATIVA 10
    @Query(value = """
            SELECT * FROM sessao
            WHERE data_sessao = :data
            """, nativeQuery = true)
    List<Sessao> buscarPorData(LocalDate data);

    // QUERY NATIVA 11
    @Query(value = """
            SELECT * FROM sessao
            WHERE terapeuta_id = :terapeutaId
            """, nativeQuery = true)
    List<Sessao> buscarPorTerapeuta(Long terapeutaId);
}