package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.ProgressoAtividadeRequestDTO;
import com.example.NeuroKids.dto.ProgressoAtividadeResponseDTO;
import com.example.NeuroKids.service.ProgressoAtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progressos")
@RequiredArgsConstructor
public class ProgressoAtividadeController {

    private final ProgressoAtividadeService progressoService;

    @Operation(summary = "Cadastrar progresso")
    @PostMapping
    public ProgressoAtividadeResponseDTO criar(
            @RequestBody @Valid
            ProgressoAtividadeRequestDTO dto) {
        return progressoService.criar(dto);
    }

    @Operation(summary = "Listar progressos")
    @GetMapping
    public List<ProgressoAtividadeResponseDTO> listarTodos() {
        return progressoService.listarTodos();
    }

    @Operation(summary = "Buscar progresso por ID")
    @GetMapping("/{id}")
    public ProgressoAtividadeResponseDTO buscarPorId(
            @PathVariable Long id) {
        return progressoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar progresso")
    @PutMapping("/{id}")
    public ProgressoAtividadeResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid
            ProgressoAtividadeRequestDTO dto) {
        return progressoService.atualizar(id, dto);
    }

    @Operation(summary = "Excluir progresso")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        progressoService.deletar(id);
    }
}