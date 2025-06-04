package com.hospitalapp.hospitalapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDTO {

    private Long hospitalId;

    @NotEmpty(message = "O nome do hospital não pode ser vazio")
    private String nome;

}
