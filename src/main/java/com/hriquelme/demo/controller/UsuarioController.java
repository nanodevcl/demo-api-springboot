package com.hriquelme.demo.controller;

import com.hriquelme.demo.model.Usuario;
import com.hriquelme.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public CompletableFuture<List<Usuario>> obtenerTodosLosUsuarios() {
        return CompletableFuture.supplyAsync(usuarioRepository::findAll);
    }

    @GetMapping("/{id}")
    public CompletableFuture<Optional<Usuario>> obtenerUsuarioPorId(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.findById(id));
    }

    @PostMapping
    public CompletableFuture<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.save(usuario));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> eliminarUsuario(@PathVariable Long id) {
        return CompletableFuture.runAsync(() -> usuarioRepository.deleteById(id));
    }
}