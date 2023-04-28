package com.miporfolio.porfolio.security.service;

import com.miporfolio.porfolio.security.entity.Usuario;
import com.miporfolio.porfolio.security.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean existsByUserName(String userName) {

        return usuarioRepository.existsByUserName(userName);
    }

    public Optional<Usuario> getByEmail(String email){

        return usuarioRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {

        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){

        usuarioRepository.save(usuario);
    }
}
