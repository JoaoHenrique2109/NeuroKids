package com.example.NeuroKids.repository;


import com.example.NeuroKids.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface
ResponsavelRepository extends JpaRepository<Responsavel, Long> {


    Optional<Responsavel> findByEmail(String email);
    Optional<Responsavel> findByCpf(String cpf);

    List<Responsavel> findByAtivo(Boolean ativo);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);


    @Query(value = """
            SELECT * FROM responsavel
            WHERE ativo = true
            """, nativeQuery = true)
    List<Responsavel> buscarResponsaveisAtivos();

    // QUERY NATIVA 5
    @Query(value = """
            SELECT * FROM responsavel
            WHERE parentesco = :parentesco
            """, nativeQuery = true)
    List<Responsavel> buscarPorParentesco(String parentesco);
}

