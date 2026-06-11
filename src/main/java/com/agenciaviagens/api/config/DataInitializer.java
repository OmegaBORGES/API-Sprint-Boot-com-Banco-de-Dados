package com.agenciaviagens.api.config;

import com.agenciaviagens.api.entity.Perfil;
import com.agenciaviagens.api.entity.Usuario;
import com.agenciaviagens.api.repository.PerfilRepository;
import com.agenciaviagens.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Perfil adminPerfil = perfilRepository.findByNome("ROLE_ADMIN")
                .orElseGet(() -> perfilRepository.save(new Perfil(null, "ROLE_ADMIN")));

        Perfil userPerfil = perfilRepository.findByNome("ROLE_USER")
                .orElseGet(() -> perfilRepository.save(new Perfil(null, "ROLE_USER")));

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPerfis(Arrays.asList(adminPerfil));
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByUsername("user").isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setPerfis(Arrays.asList(userPerfil));
            usuarioRepository.save(user);
        }
    }
}
