package com.hospitalapp.hospitalapp.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InternacaoDTO {

    @NotEmpty(message = "O nome do paciente não pode ser vazio")
    private String nome;

    @NotEmpty(message = "A especialidade da ala não pode ser vazia")
    private String especialidade;

}
