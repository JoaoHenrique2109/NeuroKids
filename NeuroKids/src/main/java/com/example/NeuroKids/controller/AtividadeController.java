package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.AtividadeRequestDTO;
import com.example.NeuroKids.dto.AtividadeResponseDTO;
import com.example.NeuroKids.service.AtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @Operation(summary = "Cadastrar atividade")
    @PostMapping
    public AtividadeResponseDTO criar(
            @RequestBody @Valid AtividadeRequestDTO dto) {
        return atividadeService.criar(dto);
    }

    @Operation(summary = "Listar atividades")
    @GetMapping
    public List<AtividadeResponseDTO> listarTodas() {
        return atividadeService.listarTodas();
    }

    @Operation(summary = "Buscar atividade por ID")
    @GetMapping("/{id}")
    public AtividadeResponseDTO buscarPorId(
            @PathVariable Long id) {
        return atividadeService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar atividade")
    @PutMapping("/{id}")
    public AtividadeResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtividadeRequestDTO dto) {
        return atividadeService.atualizar(id, dto);
    }

    @Operation(summary = "Desativar atividade")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
    }
}