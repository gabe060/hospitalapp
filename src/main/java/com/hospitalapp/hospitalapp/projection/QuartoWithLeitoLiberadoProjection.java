package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "especialidade", "codigoQuarto" })
public interface QuartoWithLeitoLiberadoProjection {
    String getEspecialidade();
    String getCodigoQuarto();
}