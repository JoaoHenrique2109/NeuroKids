package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.ComunicadoRequestDTO;
import com.example.NeuroKids.dto.ComunicadoResponseDTO;
import com.example.NeuroKids.entity.Comunicado;
import com.example.NeuroKids.entity.Paciente;
import com.example.NeuroKids.entity.Terapeuta;
import com.example.NeuroKids.repository.ComunicadoRepository;
import com.example.NeuroKids.repository.PacienteRepository;
import com.example.NeuroKids.repository.TerapeutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunicadoService {

    private final ComunicadoRepository comunicadoRepository;

    private final PacienteRepository pacienteRepository;

    private final TerapeutaRepository terapeutaRepository;

    // =========================================================
    // CREATE
    // =========================================================

    public ComunicadoResponseDTO criar(
            ComunicadoRequestDTO dto
    ) {

        Paciente paciente = pacienteRepository.findById(
                dto.getPacienteId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Paciente não encontrado"
                )
        );

        Terapeuta terapeuta = null;

        if (dto.getTerapeutaId() != null) {

            terapeuta = terapeutaRepository.findById(
                    dto.getTerapeutaId()
            ).orElseThrow(() ->
                    new RuntimeException(
                            "Terapeuta não encontrado"
                    )
            );
        }

        Comunicado comunicado = Comunicado.builder()
                .titulo(dto.getTitulo())
                .conteudo(dto.getConteudo())
                .tipo(dto.getTipo())
                .prioridade(dto.getPrioridade())
                .origem(dto.getOrigem())
                .paciente(paciente)
                .terapeuta(terapeuta)
                .visualizado(false)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

        return converterParaDTO(
                comunicadoRepository.save(comunicado)
        );
    }


    public List<ComunicadoResponseDTO> listarTodos() {

        return comunicadoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }


    public ComunicadoResponseDTO buscarPorId(Long id) {

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comunicado não encontrado"));

        return converterParaDTO(comunicado);
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public ComunicadoResponseDTO atualizar(
            Long id,
            ComunicadoRequestDTO dto
    ) {

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Comunicado não encontrado"
                        )
                );

        comunicado.setTitulo(dto.getTitulo());
        comunicado.setConteudo(dto.getConteudo());
        comunicado.setTipo(dto.getTipo());
        comunicado.setPrioridade(dto.getPrioridade());
        comunicado.setOrigem(dto.getOrigem());
        comunicado.setAtualizadoEm(LocalDateTime.now());

        return converterParaDTO(
                comunicadoRepository.save(comunicado)
        );
    }

    public ComunicadoResponseDTO visualizar(
            Long id
    ) {

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Comunicado não encontrado"
                        )
                );

        comunicado.setVisualizado(true);

        comunicado.setVisualizadoEm(
                LocalDateTime.now()
        );

        return converterParaDTO(
                comunicadoRepository.save(comunicado)
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deletar(Long id) {

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Comunicado não encontrado"
                        )
                );

        comunicadoRepository.delete(comunicado);
    }

    // =========================================================
    // CONVERSOR DTO
    // =========================================================

    private ComunicadoResponseDTO converterParaDTO(
            Comunicado comunicado
    ) {

        return ComunicadoResponseDTO.builder()
                .id(comunicado.getId())
                .titulo(comunicado.getTitulo())
                .conteudo(comunicado.getConteudo())
                .tipo(comunicado.getTipo())
                .prioridade(comunicado.getPrioridade())
                .origem(comunicado.getOrigem())
                .visualizado(comunicado.getVisualizado())
                .visualizadoEm(
                        comunicado.getVisualizadoEm()
                )
                .paciente(
                        comunicado.getPaciente().getNome()
                )
                .terapeuta(
                        comunicado.getTerapeuta() != null
                                ? comunicado.getTerapeuta().getNome()
                                : null
                )
                .criadoEm(comunicado.getCriadoEm())
                .build();
    }
}