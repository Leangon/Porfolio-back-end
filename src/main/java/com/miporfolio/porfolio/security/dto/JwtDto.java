package com.miporfolio.porfolio.security.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
//Cuando haganmos un login nos va devolver el responseEntity del contralador, un jwt.

@Getter @Setter
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authoritys;

    public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authoritys) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.authoritys = authoritys;
    }
    
    
}
