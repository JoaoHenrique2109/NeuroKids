package com.example.NeuroKids.service;

import com.example.NeuroKids.dto.AtividadeRequestDTO;
import com.example.NeuroKids.dto.AtividadeResponseDTO;
import com.example.NeuroKids.entity.Atividade;
import com.example.NeuroKids.repository.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;


    public AtividadeResponseDTO criar(
            AtividadeRequestDTO dto) {


        Atividade atividade = Atividade.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .instrucoes(dto.getInstrucoes())
                .tipo(dto.getTipo())
                .dificuldade(dto.getDificuldade())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .ativa(true)
                .build();

        return converterParaDTO(
                atividadeRepository.save(atividade)
        );
    }

    public List<AtividadeResponseDTO> listarTodas() {

        return atividadeRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }


    public AtividadeResponseDTO buscarPorId(Long id) {

        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Atividade não encontrada"));

        return converterParaDTO(atividade);
    }


    public AtividadeResponseDTO atualizar(Long id, AtividadeRequestDTO dto) {

        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Atividade não encontrada"));


        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricao(dto.getDescricao());
        atividade.setInstrucoes(dto.getInstrucoes());
        atividade.setTipo(dto.getTipo());
        atividade.setDificuldade(dto.getDificuldade());
        atividade.setCriadoEm(LocalDateTime.now());
        atividade.setAtualizadoEm(LocalDateTime.now());

        return converterParaDTO(atividadeRepository.save(atividade));
    }
    @Transactional
    public void deletar(Long id) {

        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Atividade não encontrada"));

        atividade.setAtiva(false);

        atividadeRepository.save(atividade);
    }


    private AtividadeResponseDTO converterParaDTO(Atividade atividade) {

        return AtividadeResponseDTO.builder()
                .id(atividade.getId())
                .titulo(atividade.getTitulo())
                .descricao(atividade.getDescricao())
                .instrucoes(atividade.getInstrucoes())
                .tipo(atividade.getTipo())
                .dificuldade(atividade.getDificuldade())
                .tipo(atividade.getTipo())
                .quantidadeProgressos(
                        atividade.getProgressos() != null
                                ? atividade.getProgressos().size()
                                : 0)
                .criadoEm(atividade.getCriadoEm())
                .build();
    }
}
