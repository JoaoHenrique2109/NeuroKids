package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.PacienteRequestDTO;
import com.example.NeuroKids.dto.PacienteResponseDTO;
import com.example.NeuroKids.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @Operation(summary = "Cadastrar novo paciente")
    @PostMapping
    public PacienteResponseDTO criar(
            @RequestBody @Valid PacienteRequestDTO dto
    ) {
        return pacienteService.criar(dto);
    }

    @Operation(summary = "Listar todos os pacientes")
    @GetMapping
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteService.listarTodos();
    }

    @Operation(summary = "Buscar paciente por ID")
    @GetMapping("/{id}")
    public PacienteResponseDTO buscarPorId(
            @PathVariable Long id
    ) {
        return pacienteService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar paciente")
    @PutMapping("/{id}")
    public PacienteResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PacienteRequestDTO dto
    ) {
        return pacienteService.atualizar(id, dto);
    }

    @Operation(summary = "Desativar paciente")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}
