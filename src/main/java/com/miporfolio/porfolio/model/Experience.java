package com.miporfolio.porfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orden")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orden;

    @NotNull
    private String company;

    @NotNull
    private String position;

    @NotNull
    private String description;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    @Column(name = "url_logo")
    private String urlLogo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "persona_id")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Persona persona;

    public Experience(Integer orden, String company, String position, String description, String startDate, String endDate, String urlLogo, Persona persona) {
        this.orden = orden;
        this.company = company;
        this.position = position;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.urlLogo = urlLogo;
        this.persona = persona;

    }
}
