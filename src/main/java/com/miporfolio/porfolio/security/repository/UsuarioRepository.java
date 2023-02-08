package com.miporfolio.porfolio.security.repository;

import com.miporfolio.porfolio.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> finByNombreUsuario(String userName);
   
    boolean existsByNombreUsuario(String userName);
    
    Optional<Usuario> finByEmail(String email);
    
    boolean existsByEmail(String email);
    
    public void createUser(Usuario usuario);
}
