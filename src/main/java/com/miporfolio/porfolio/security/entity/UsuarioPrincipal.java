package com.miporfolio.porfolio.security.entity;

// Va a implementar los privilegios de cada usuario, la encargada de generar la seguridad.

import jakarta.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter @Setter
public class UsuarioPrincipal implements UserDetails{
    
    @NotNull
    @Column(unique = true)
    private String userName;
    
    @NotNull
    private String email;
    
    @NotNull
    private String password;
    
    private Collection<? extends GrantedAuthority> authoritys;
    
     public UsuarioPrincipal() {
    }

    public UsuarioPrincipal(String userName, String email, String password, Collection<? extends GrantedAuthority> authoritys) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authoritys = authoritys;
    }
    
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authoritys = 
                usuario.getRoles()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                        .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUserName(), usuario.getEmail(), usuario.getPassword(), authoritys);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritys;
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

   
    
}
