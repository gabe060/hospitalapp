package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "nomeHospital", "nomePaciente", "especialidade", "codigoQuarto", "dataInternacao" })
public interface InfoPacienteProjection {
    String getNomePaciente();
    String getCodigoQuarto();
    String getEspecialidade();
    String getNomeHospital();
    LocalDateTime getDataInternacao();
}