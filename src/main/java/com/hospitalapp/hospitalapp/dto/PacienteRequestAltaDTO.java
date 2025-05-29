package com.hospitalapp.hospitalapp.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteRequestAltaDTO {

    @NotNull(message = "O id do paciente não pode ser vazio")
    private Long pacienteId;

}
