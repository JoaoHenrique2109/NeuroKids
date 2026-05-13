package com.example.NeuroKids.controller;

import com.example.NeuroKids.dto.ResponsavelRequestDTO;
import com.example.NeuroKids.dto.ResponsavelResponseDTO;
import com.example.NeuroKids.service.ResponsavelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @Operation(summary = "Cadastrar responsável")
    @PostMapping
    public ResponsavelResponseDTO criar(
            @RequestBody @Valid ResponsavelRequestDTO dto
    ) { return responsavelService.criar(dto);}

    @Operation(summary = "Listar responsáveis")
    @GetMapping
    public List<ResponsavelResponseDTO> listarTodos() {
        return responsavelService.listarTodos();
    }

    @Operation(summary = "Buscar responsável por ID")
    @GetMapping("/{id}")
    public ResponsavelResponseDTO buscarPorId(
            @PathVariable Long id
    ) {
        return responsavelService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar responsável")
    @PutMapping("/{id}")
    public ResponsavelResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ResponsavelRequestDTO dto
    ) {
        return responsavelService.atualizar(id, dto);
    }

    @Operation(summary = "Desativar responsável")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        responsavelService.deletar(id);
    }
}
