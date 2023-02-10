package com.miporfolio.porfolio.security.repository;

import com.miporfolio.porfolio.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName);

    boolean existsByUserName(String userName);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

}
