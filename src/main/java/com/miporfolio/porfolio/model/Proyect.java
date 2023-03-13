package com.miporfolio.porfolio.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Proyect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "url_repository")
    private String urlRepository;

    @NotNull
    private  String urlDemo;

    @NotNull
    private String urlImage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Persona persona;

    public Proyect(String name, String description, String urlRepository, String urlDemo, String urlImage, Persona persona) {
        this.name = name;
        this.description = description;
        this.urlRepository = urlRepository;
        this.urlDemo = urlDemo;
        this.urlImage = urlImage;
        this.persona = persona;
    }
}
