package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerapeutaRepository
        extends JpaRepository<Terapeuta, Long> {

    // Busca por email
    Optional<Terapeuta> findByEmail(String email);

    // Busca por registro profissional
    Optional<Terapeuta> findByRegistroProfissional(
            String registroProfissional
    );

    // Busca terapeutas ativos/inativos
    List<Terapeuta> findByAtivo(Boolean ativo);

    // Busca por especialidade
    List<Terapeuta> findByEspecialidade(
            Terapeuta.Especialidade especialidade
    );

    // Verifica email existente
    boolean existsByEmail(String email);

    // Verifica registro existente
    boolean existsByRegistroProfissional(
            String registroProfissional
    );

    // Busca por especialidade
    @Query("""
            SELECT t
            FROM Terapeuta t
            WHERE t.especialidade = :especialidade
            """)
    List<Terapeuta> buscarPorEspecialidade(
            @Param("especialidade")
            Terapeuta.Especialidade especialidade
    );

    // Busca apenas terapeutas ativos
    @Query("""
            SELECT t
            FROM Terapeuta t
            WHERE t.ativo = true
            """)
    List<Terapeuta> buscarTerapeutasAtivos();

    // Busca por nome
    @Query("""
            SELECT t
            FROM Terapeuta t
            WHERE LOWER(t.nome)
            LIKE LOWER(CONCAT('%', :nome, '%'))
            """)
    List<Terapeuta> buscarPorNome(
            @Param("nome") String nome
    );

    // Busca terapeuta ativo por ID
    Optional<Terapeuta> findByIdAndAtivo(
            Long id,
            Boolean ativo
    );
}
