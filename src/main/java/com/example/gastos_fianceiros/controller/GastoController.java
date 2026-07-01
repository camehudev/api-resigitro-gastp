package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.GastoDTO;
import com.example.gastos_fianceiros.domain.model.Gasto;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoController {

    private GastoRepository repository;

    // Injeção de dependência via construtor
    public GastoController(GastoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Gasto> criar(@RequestBody GastoDTO dto) {
        Gasto gasto = new Gasto(dto.descricao(), dto.valor(), dto.data());
        Gasto salvo = repository.save(gasto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Gasto> listar() {
        return repository.findAll();
    }
}