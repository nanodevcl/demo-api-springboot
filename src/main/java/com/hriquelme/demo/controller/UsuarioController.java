package com.hriquelme.demo.controller;

import com.hriquelme.demo.model.Usuario;
import com.hriquelme.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Qualifier("taskExecutor")
    private Executor taskExecutor;

    @Async("taskExecutor")
    @GetMapping
    public CompletableFuture<List<Usuario>> obtenerTodosLosUsuarios() {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.findAll(), taskExecutor);
    }

    @Async("taskExecutor")
    @GetMapping("/{id}")
    public CompletableFuture<Optional<Usuario>> obtenerUsuarioPorId(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.findById(id), taskExecutor);
    }

    @Async("taskExecutor")
    @PostMapping
    public CompletableFuture<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.save(usuario), taskExecutor);
    }

    @Async("taskExecutor")
    @DeleteMapping("/{id}")
    public CompletableFuture<Void> eliminarUsuario(@PathVariable Long id) {
        return CompletableFuture.runAsync(() -> usuarioRepository.deleteById(id), taskExecutor);
    }
}