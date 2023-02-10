package com.miporfolio.porfolio.security.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NuevoUsuario {
    @NotBlank
    private String userName;

    @Email
    private String email;
   
    @NotBlank
    private String password;
    
    private Set<String> roles = new HashSet<>();
}
