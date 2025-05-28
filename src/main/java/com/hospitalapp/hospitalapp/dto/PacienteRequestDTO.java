package com.hospitalapp.hospitalapp.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteRequestDTO {

    @NotEmpty(message = "O nome do paciente não pode ser vazio")
    private String nome;

}
