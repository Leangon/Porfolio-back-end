package com.miporfolio.porfolio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "url_image")
    private String urlImage;

    @NotNull
    private int percent;

    //JoinColumns indica que esta entidad va a ser la propietaria de la relacion y por ende va a llevar la clave foranea
    //que apunte a la entidad persona.
    //El @JsonProperty se agrega para evitar el error que puede arrojar FetchType.LAZY
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Persona persona;

    public Skill() {
    }

    public Skill(String name, String urlImage, int percent, Persona persona) {
        this.name = name;
        this.urlImage = urlImage;
        this.percent = percent;
        this.persona = persona;
    }
}
