package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.CategoriaSomaDTO;
import com.example.gastos_fianceiros.DTO.GastoDTO;
import com.example.gastos_fianceiros.application.service.GastoService;
import com.example.gastos_fianceiros.domain.model.Gasto;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoController {

    private final GastoRepository repository;
    private final GastoService service;

    // Injeção via construtor unificada (Boas práticas do Spring Boot moderno)
    public GastoController(GastoRepository repository, GastoService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Gasto> criar(@RequestBody GastoDTO dto) {
        System.out.println("Método criar acionado com sucesso: " + dto);

        Gasto gasto = new Gasto(
            dto.categoria(),
            dto.data_gasto(),
            dto.valor(),
            dto.total_parcelas(),
            dto.parcela_atual(),
            dto.descricao()
        );

        Gasto salvo = repository.save(gasto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Gasto> listar() {       
        return repository.findAll();
    }

    @GetMapping("/resumo")
    public List<CategoriaSomaDTO> resumo() {
        return service.obterResumoPorCategoria();
    }
}