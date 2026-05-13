package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.SessaoRequestDTO;
import com.example.NeuroKids.dto.SessaoResponseDTO;
import com.example.NeuroKids.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @Operation(summary = "Cadastrar sessão terapêutica")
    @PostMapping
    public SessaoResponseDTO criar(
            @RequestBody @Valid SessaoRequestDTO dto) {
        return sessaoService.criar(dto);
    }

    @Operation(summary = "Listar sessões")
    @GetMapping
    public List<SessaoResponseDTO> listarTodos() {
        return sessaoService.listarTodos();
    }

    @Operation(summary = "Buscar sessão por ID")
    @GetMapping("/{id}")
    public SessaoResponseDTO buscarPorId(
            @PathVariable Long id) {
        return sessaoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar sessão")
    @PutMapping("/{id}")
    public SessaoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SessaoRequestDTO dto) {
        return sessaoService.atualizar(id, dto);
    }

    @Operation(summary = "Remover sessão")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sessaoService.deletar(id);
    }
}
