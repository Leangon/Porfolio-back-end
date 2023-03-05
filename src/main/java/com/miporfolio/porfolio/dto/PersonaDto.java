package com.miporfolio.porfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

    private String name;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String urlImage;

    @NotBlank
    private String dateBirth;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String aboutMe;
}
