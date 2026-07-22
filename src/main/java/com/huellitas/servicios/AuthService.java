package com.huellitas.servicios;

import com.huellitas.excepciones.NegocioException;
import com.huellitas.filter.JwtUtil;
import com.huellitas.modelos.Usuario;
import com.huellitas.modelos.auth.AuthRequest;
import com.huellitas.modelos.auth.AuthResponse;
import com.huellitas.modelos.auth.RegisterRequest;
import com.huellitas.repositorios.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByUserName(request.getUserName())
                .orElseThrow();

        String token = jwtUtil.generateToken(usuario);

        return new AuthResponse(token, usuario.getUserName(), usuario.getName(), usuario.getRol().name());
    }

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByUserName(request.getUserName())) {
            throw new NegocioException("El usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setUserName(request.getUserName());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Usuario.Rol.RECEPCIONISTA);

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario);

        return new AuthResponse(token, usuario.getUsername(), usuario.getName(), usuario.getRol().name());
    }
}
