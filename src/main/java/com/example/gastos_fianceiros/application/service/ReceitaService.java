package com.example.gastos_fianceiros.application.service;

import com.example.gastos_fianceiros.DTO.ReceitaDTO;
import com.example.gastos_fianceiros.DTO.ResumoFinanceiroDTO;
import com.example.gastos_fianceiros.domain.model.Receitas;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;
import com.example.gastos_fianceiros.infrastructure.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;
    private GastoRepository gastoRepository;
 

    // 1. GET: Listar todas as receitas convertidas para DTO
    public List<ReceitaDTO> listarTodasReceitas() {
        List<Receitas> receitas = receitaRepository.findAllByOrderByIdDesc();
        return receitas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // 2. GET: Buscar por ID
    public ReceitaDTO buscarReceitaId(Long id) {
        Receitas receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada com o ID: " + id));
        return converterParaDTO(receita);
    }

    // 3. POST: Salvar uma nova receita
    public ReceitaDTO salvarReceita(ReceitaDTO receitaDTO) {
        Receitas receita = converterParaEntidade(receitaDTO);
        Receitas salva = receitaRepository.save(receita);
        return converterParaDTO(salva);
    }

    // 4. UPDATE: Atualizar uma receita existente
    public ReceitaDTO atualizarReceita(Long id, ReceitaDTO receitaDTO) {
        Receitas existente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada para atualização com ID: " + id));
        
        existente.setCategoria(receitaDTO.getCategoria());
        existente.setDescricao(receitaDTO.getDescricao());
        existente.setValor(receitaDTO.getValor());
        existente.setData(receitaDTO.getData());

        Receitas atualizada = receitaRepository.save(existente);
        return converterParaDTO(atualizada);
    }

    // 5. DELETE: Excluir uma receita
    public void deletarReceita(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new RuntimeException("Não foi possível excluir. Receita não encontrada com ID: " + id);
        }
        receitaRepository.deleteById(id);
    }

    // Métodos auxiliares de conversão corrigidos para suportar o ID
    private ReceitaDTO converterParaDTO(Receitas receita) {
        return new ReceitaDTO(
                receita.getId(), // Incluído o ID para retornar o objeto completo ao frontend
                receita.getCategoria(),
                receita.getDescricao(),                
                receita.getValor(),
                receita.getData()
        );
    }

    private Receitas converterParaEntidade(ReceitaDTO dto) {
        Receitas receita = new Receitas();
        receita.setId(dto.getId());
        receita.setCategoria(dto.getCategoria());
        receita.setDescricao(dto.getDescricao());
        receita.setValor(dto.getValor());
        receita.setData(dto.getData());
        return receita;
    }


    public BigDecimal obterValorTotalGeralReceitas() {
        BigDecimal total = receitaRepository.somarValorTotalGeral();
        // Evita retornar null para o frontend caso a tabela esteja vazia
        return total != null ? total : BigDecimal.ZERO;
    }


    public void ResumoFinanceiroService(GastoRepository gastoRepository, ReceitaRepository receitaRepository) {
        this.gastoRepository = gastoRepository;
        this.receitaRepository = receitaRepository;
    }


    public ResumoFinanceiroDTO obterResumoFinanceiro() {
        // Busca total de gastos (tratando null caso a tabela esteja vazia)
        BigDecimal totalGastos = gastoRepository.somarValorTotalGeral();
        if (totalGastos == null) {
            totalGastos = BigDecimal.ZERO;
        }

        // Busca total de receitas (tratando null)
        BigDecimal totalReceitas = receitaRepository.somarValorTotalGeral();
        if (totalReceitas == null) {
            totalReceitas = BigDecimal.ZERO;
        }

        // Calcula o saldo (Receitas - Gastos)
        BigDecimal saldo = totalReceitas.subtract(totalGastos);

        return new ResumoFinanceiroDTO(totalReceitas, totalGastos, saldo);
    }


    

}