package com.miporfolio.porfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String university;

    @NotNull
    private String title;

    @NotNull
    @Column(name = "url_logo")
    private String urlLogo;

    @NotNull
    @Column(name = "start_date")
    private String startDate;

    @NotNull
    @Column(name = "end_date")
    private String endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Persona persona;

    public Education(String university, String title, String urlLogo, String startDate, String endDate, Persona persona) {
        this.university = university;
        this.title = title;
        this.urlLogo = urlLogo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.persona = persona;
    }
}
