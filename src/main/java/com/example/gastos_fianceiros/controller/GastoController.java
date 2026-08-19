package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.CategoriaSomaDTO;
import com.example.gastos_fianceiros.DTO.GastoDTO;
import com.example.gastos_fianceiros.application.service.GastoService;
import com.example.gastos_fianceiros.domain.model.Gasto;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
            dto.valor(),
            dto.descricao(),
            dto.data_gasto(),
            dto.data_criacao(),           
            dto.total_parcelas(),
            dto.parcela_atual()
           
        );

        Gasto salvo = repository.save(gasto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Gasto> listar() {       
        return repository.findAllByOrderByIdDesc();
    }

    @GetMapping("/resumo")
    public List<CategoriaSomaDTO> resumo() {     
        return service.obterResumoPorCategoria();
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> obterTotalGeral() {        
        BigDecimal totalGeral = service.obterValorTotalGeral();
        return ResponseEntity.ok(totalGeral);
    }
}