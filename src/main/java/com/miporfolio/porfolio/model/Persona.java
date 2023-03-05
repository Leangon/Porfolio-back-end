package com.miporfolio.porfolio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miporfolio.porfolio.security.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generator")
    @SequenceGenerator(name = "persona_generator", sequenceName = "persona_seq", allocationSize = 1)
    private int id;

    @NotNull
    @Column(name = "full_name")
    private String name;

    @NotNull
    @Column(length = 500)
    private String title;

    @NotNull
    @Column(length = 600)
    private String description;

    @NotNull
    @Column(name = "url_image", length = 600)
    private String urlImage;

    @NotNull
    @Column(name = "date_birth")
    private String dateBirth;

    @NotNull
    @Column (name = "phone_num")
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "about_me", length = 1000)
    private String aboutMe;

    @OneToOne(mappedBy = "persona", fetch =  FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Skill> skills = new HashSet<>();

    public Persona() {}

    public Persona(int id, String name, String title, String description, String urlImage, String dateBirth,
                   String phoneNumber, String email, String aboutMe) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aboutMe = aboutMe;
    }

}