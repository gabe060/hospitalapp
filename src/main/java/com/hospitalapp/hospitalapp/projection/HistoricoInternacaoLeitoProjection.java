package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "nomePaciente", "codigoLeito", "dataInternacao", "dataAlta" })
public interface HistoricoInternacaoLeitoProjection {
    String getCodigoLeito();
    String getNomePaciente();
    LocalDateTime getDataInternacao();
    LocalDateTime getDataAlta();
}