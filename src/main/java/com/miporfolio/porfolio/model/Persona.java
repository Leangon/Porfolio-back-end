package com.miporfolio.porfolio.model;

import com.miporfolio.porfolio.security.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generator")
    @SequenceGenerator(name = "persona_generator", sequenceName = "persona_seq", allocationSize = 1)
    private int id;
    
    @Column(name = "full_name")
    private String name;
    
    private String title;
    
    private String description;
    
    @Column(name = "url_image")
    private String urlImage;
    
    @Column(name = "date_birth")
    private Date dateBirh;
    
    @Column (name = "phone_num")
    private String phoneNumber;
    
    private String email;
    
    @Column(name = "about_me")
    private String aboutMe;
    
    @OneToOne(mappedBy = "persona")
    private Usuario usuario;
    
    public Persona() {}

    public Persona(int id, String name, String title, String description, String urlImage, Date dateBirh, String phoneNumber, String email, String aboutMe, Usuario usuario) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.dateBirh = dateBirh;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aboutMe = aboutMe;
        this.usuario = usuario;
    }
    
    
    
}
