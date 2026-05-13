package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.TerapeutaRequestDTO;
import com.example.NeuroKids.dto.TerapeutaResponseDTO;
import com.example.NeuroKids.service.TerapeutaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terapeutas")
@RequiredArgsConstructor
public class TerapeutaController {

    private final TerapeutaService terapeutaService;

    @Operation(summary = "Cadastrar terapeuta")
    @PostMapping

    public TerapeutaResponseDTO criar(
            @RequestBody @Valid TerapeutaRequestDTO dto) {
        return terapeutaService.criar(dto);
    }

    @Operation(summary = "Listar terapeutas")
    @GetMapping

    public List<TerapeutaResponseDTO> listarTodos() {
        return terapeutaService.listarTodos();
    }

    @Operation(summary = "Buscar terapeuta por ID")
    @GetMapping("/{id}")

    public TerapeutaResponseDTO buscarPorId(@PathVariable Long id) {
        return terapeutaService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar terapeuta")
    @PutMapping("/{id}")

    public TerapeutaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid TerapeutaRequestDTO dto) {
        return terapeutaService.atualizar(id, dto);
    }

    @Operation(summary = "Desativar terapeuta")
    @DeleteMapping("/{id}")

    public void deletar(@PathVariable Long id) {
        terapeutaService.deletar(id);
    }
}