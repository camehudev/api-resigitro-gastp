package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.ReceitaDTO;
import com.example.gastos_fianceiros.application.service.ReceitaService;
import com.example.gastos_fianceiros.domain.model.Receitas;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    // GET: Listar todas as receitas
    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarTodas() {
        // List<ReceitaDTO> receitas = receitaService.listarTodasReceitas(); // Ajuste se seu service retorna DTO direto
        List<ReceitaDTO> listaDTO = receitaService.listarTodasReceitas();
        return ResponseEntity.ok(listaDTO);
    }

    // GET: Buscar receita por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        ReceitaDTO receitaDTO = receitaService.buscarReceitaId(id);
        return ResponseEntity.ok(receitaDTO);
    }

    // POST: Criar nova receita
    @PostMapping
    public ResponseEntity<ReceitaDTO> criar(@Valid @RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO novaReceita = receitaService.salvarReceita(receitaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }

    // PUT: Atualizar receita existente
    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO receitaAtualizada = receitaService.atualizarReceita(id, receitaDTO);
        return ResponseEntity.ok(receitaAtualizada);
    }

    // DELETE: Excluir receita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }
}
