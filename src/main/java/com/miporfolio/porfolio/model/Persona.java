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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "full_name")
    private String name;

    @NotNull
    private String title;

    @NotNull
    @Column(length = 350)
    private String description;

    @NotNull
    @Column(name = "url_image")
    private String urlImage;

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

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Education> educations = new HashSet<>();

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Proyect> proyects = new HashSet<>();

    public Persona() {}

    public Persona(String name, String title, String description, String urlImage, String phoneNumber, String email, String aboutMe) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aboutMe = aboutMe;
    }
}