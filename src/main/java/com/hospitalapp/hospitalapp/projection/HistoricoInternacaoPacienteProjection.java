package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "nomePaciente", "especialidade", "dataInternacao", "dataAlta" })
public interface HistoricoInternacaoPacienteProjection {
    String getNomePaciente();
    String getEspecialidade();
    LocalDateTime getDataInternacao();
    LocalDateTime getDataAlta();
}
