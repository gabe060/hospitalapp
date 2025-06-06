package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "nomePaciente", "codigoQuarto" })
public interface PacienteQuartoProjection {
    String getNomePaciente();
    String getCodigoQuarto();
}
