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
    
    public Optional<Usuario> getByNombreUsuario(String userName) {
        return usuarioRepository.finByNombreUsuario(userName);
    }
    
    public boolean existsByNombreUsuario(String userName) {
        return usuarioRepository.existsByNombreUsuario(userName);
    }
    
    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.finByEmail(email);
    }
    
     public boolean existsByEmail(String nombreEmail) {
        return usuarioRepository.existsByEmail(nombreEmail);
    }
     
     public void createUser(Usuario usuario){
         usuarioRepository.save(usuario);
     }
}
