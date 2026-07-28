package com.example.gastos_fianceiros.application.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gastos_fianceiros.domain.model.Usuario;
import com.example.gastos_fianceiros.infrastructure.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependências via construtor
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Salvar / Cadastrar Usuário
    @Transactional
    public Usuario salvar(Usuario usuario) {
        // Exemplo de regra de negócio: Criptografar a senha antes de persistir
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    // 2. Listar Todos
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // 3. Buscar por ID (com tratamento básico de exceção se não encontrar)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // 4. Atualizar Usuário
    @Transactional
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());

        // Se uma nova senha foi informada, criptografa e atualiza
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // 5. Deletar Usuário
    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id); // Garante que existe antes de excluir
        usuarioRepository.delete(usuario);
    }
}
