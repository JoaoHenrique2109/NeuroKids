package com.example.NeuroKids.repository;

import com.example.NeuroKids.entity.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {


    Optional<Terapeuta> findByEmail(String email);

    Optional<Terapeuta> findByRegistroProfissional(String registro);

    List<Terapeuta> findByAtivo(Boolean ativo);
    List<Terapeuta> findByEspecialidade(Terapeuta.Especialidade especialidade);
    boolean existsByEmail(String email);
    boolean existsByRegistroProfissional(String registro);



    @Query(value = """
            SELECT * FROM terapeuta
            WHERE especialidade = :especialidade
            """, nativeQuery = true)
    List<Terapeuta> buscarPorEspecialidade(
            String especialidade);

    @Query(value = """
            SELECT * FROM terapeuta
            WHERE ativo = true
            """, nativeQuery = true)
    List<Terapeuta> buscarTerapeutasAtivos();

    @Query(value = """
            SELECT * FROM terapeuta
            WHERE nome LIKE %:nome%
            """, nativeQuery = true)
    List<Terapeuta> buscarPorNome(String nome);
}
