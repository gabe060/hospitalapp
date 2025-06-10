package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "nomePaciente", "especialidade", "dataInternacao", "diasInternado" })
public interface PacientesInternadosAlfabeticoProjection {
    String getNomePaciente();
    String getEspecialidade();
    LocalDateTime getDataInternacao();
    Integer getDiasInternado();
}
