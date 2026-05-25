package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Busca todos os pacientes ativos ou inativos
    List<Paciente> findByAtivo(Boolean ativo);

    // Busca pacientes pelo diagnóstico (ignorando maiúsculas)
    List<Paciente> findByDiagnosticoContainingIgnoreCase(String diagnostico);


    @Query(value = """
        SELECT *
        FROM paciente p
        WHERE p.responsavel_id = :responsavelId
          AND p.ativo = 1
        """, nativeQuery = true)
    List<Paciente> findPacientesAtivosByResponsavel(
            @Param("responsavelId") Long responsavelId);


    @Query(value = """
        SELECT p.*
        FROM paciente p
        INNER JOIN paciente_terapeuta pt
            ON p.id = pt.paciente_id
        WHERE pt.terapeuta_id = :terapeutaId
          AND p.ativo = 1
        """, nativeQuery = true)
    List<Paciente> findPacientesByTerapeuta(
            @Param("terapeutaId") Long terapeutaId);


    @Query(value = """
            SELECT p.id, p.nome, p.diagnostico,
                   COUNT(pa.id) AS total_atividades_concluidas
            FROM paciente p
            LEFT JOIN progresso_atividades pa ON pa.paciente_id = p.id
                AND pa.status = 'CONCLUIDO'
            WHERE p.ativo = 1
            GROUP BY p.id, p.nome, p.diagnostico
            ORDER BY total_atividades_concluidas DESC
            LIMIT :limite
            """, nativeQuery = true)
    List<Object[]> findRankingPacientesMaisAtivos(@Param("limite") int limite);


    @Query(value = """
            SELECT p.id, p.nome, p.diagnostico, MAX(pa.ultima_execucao) AS ultima_atividade
            FROM paciente p
            LEFT JOIN progresso_atividade pa ON pa.paciente_id = p.id
            WHERE p.ativo = 1
            GROUP BY p.id, p.nome, p.diagnostico
            HAVING ultima_atividade IS NULL
               OR ultima_atividade < DATE_SUB(NOW(), INTERVAL :dias DAY)
            """, nativeQuery = true)
    List<Object[]> findPacientesSemAtividadeRecente(@Param("dias") int dias);

   // busca por nome do paciente na lista geral
    @Query(value = """
            SELECT p.* FROM paciente p
            INNER JOIN responsavei r ON r.id = p.responsavel_id
            WHERE p.ativo = 1
              AND (p.nome LIKE CONCAT('%', :termo, '%')
               OR r.nome LIKE CONCAT('%', :termo, '%'))
            """, nativeQuery = true)
    List<Paciente> findByNomeOuResponsavel(@Param("termo") String termo);

    // Verifica se já existe paciente com este nome no mesmo responsável
    boolean existsByNomeIgnoreCaseAndResponsavelId(String nome, Long responsavelId);
    //busca pelo ID
    Optional<Paciente> findByIdAndAtivo(Long id, Boolean ativo);
}