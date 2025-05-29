package com.hospitalapp.hospitalapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewAlaDTO {

    @NotEmpty(message = "A especialidade da ala não pode ser vazia")
    private String especialidade;

    @NotNull(message = "A quantidade de quartos não pode ser vazia")
    private Integer quantidadeQuartos;

    @NotNull(message = "A quantidade de leitos não pode ser vazia")
    private Integer quantidadeLeitosPorQuarto;


}
