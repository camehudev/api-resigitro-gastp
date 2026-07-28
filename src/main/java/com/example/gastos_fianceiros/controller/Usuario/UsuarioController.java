package com.example.gastos_fianceiros.controller.Usuario;


import com.example.gastos_fianceiros.DTO.UsuarioDTO; // Substitua pelo seu DTO ou Entidade correspondente
import com.example.gastos_fianceiros.domain.model.Usuario;   // Se utilizar a entidade diretamente ou DTO
import com.example.gastos_fianceiros.application.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Injeção de dependência via construtor (Boa prática em Engenharia de Software)
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Cadastrar Usuário (POST)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
        Usuario novoUsuario = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // 2. Listar Todos os Usuários (GET)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // 3. Buscar Usuário por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // 4. Editar / Atualizar Usuário (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizar(id, usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }

    // 5. Remover Usuário (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content após exclusão bem-sucedida
    }
}
