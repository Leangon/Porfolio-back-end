/*package com.miporfolio.porfolio.util;

import com.miporfolio.porfolio.security.entity.Rol;
import com.miporfolio.porfolio.security.enums.RolNombre;
import com.miporfolio.porfolio.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRol implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {

        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.rolSave(rolAdmin);
        rolService.rolSave(rolUser);

    }
}*/
