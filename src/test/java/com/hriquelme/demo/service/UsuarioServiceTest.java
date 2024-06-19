package com.hriquelme.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hriquelme.demo.model.Usuario;
import com.hriquelme.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testObtenerTodosLosUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNombre("Juan");
        usuario1.setEmail("juanperez@gmail.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Maria");
        usuario2.setEmail("mariaperez@gmail.com");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.obtenerTodosLosUsuarios();
        assertEquals(2, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        assertEquals("Maria", result.get(1).getNombre());
        assertEquals("mariaperez@gmail.com", result.get(2).getEmail());
    }

    @Test
     void testObtenerUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.obtenerUsuarioPorId(1L);
        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
    }

    @Test
     void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.guardarUsuario(usuario);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
     void testEliminarUsuario() {
        Long userId = 1L;
        doNothing().when(usuarioRepository).deleteById(userId);

        usuarioService.eliminarUsuario(userId);

        verify(usuarioRepository, times(1)).deleteById(userId);
    }
}