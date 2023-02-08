
package com.miporfolio.porfolio.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NuevoUsuario {
    @NotBlank
    private String nombreUsuario;
    
    @NotNull
    @NotBlank
    @Email
    private String email;
   
    @NotBlank
    private String password;
    
    private Set<String> roles = new HashSet<>();
}
