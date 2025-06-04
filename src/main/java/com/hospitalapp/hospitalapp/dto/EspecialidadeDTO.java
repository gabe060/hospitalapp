package com.hospitalapp.hospitalapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EspecialidadeDTO {

    @NotEmpty(message = "A especialidade da ala não pode ser vazia")
    private String especialidade;
}
