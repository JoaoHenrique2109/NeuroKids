package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.ComunicadoRequestDTO;
import com.example.NeuroKids.dto.ComunicadoResponseDTO;
import com.example.NeuroKids.service.ComunicadoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunicados")
@RequiredArgsConstructor
public class ComunicadoController {

    private final ComunicadoService comunicadoService;

    @Operation(summary = "Cadastrar comunicado")
    @PostMapping
    public ComunicadoResponseDTO criar(
            @RequestBody @Valid ComunicadoRequestDTO dto
    ) {
        return comunicadoService.criar(dto);
    }

    @Operation(summary = "Listar comunicados")
    @GetMapping
    public List<ComunicadoResponseDTO> listarTodos() {
        return comunicadoService.listarTodos();
    }

    @Operation(summary = "Buscar comunicado por ID")
    @GetMapping("/{id}")
    public ComunicadoResponseDTO buscarPorId(
            @PathVariable Long id
    ) {
        return comunicadoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar comunicado")
    @PutMapping("/{id}")
    public ComunicadoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ComunicadoRequestDTO dto
    ) {
        return comunicadoService.atualizar(id, dto);
    }

    @Operation(summary = "Marcar comunicado como visualizado")
    @PatchMapping("/{id}/visualizar")
    public ComunicadoResponseDTO visualizar(
            @PathVariable Long id
    ) {
        return comunicadoService.visualizar(id);
    }

    @Operation(summary = "Excluir comunicado")
    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable Long id
    ) {
        comunicadoService.deletar(id);
    }
}
