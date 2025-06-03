package com.hospitalapp.hospitalapp.projection;

import java.time.LocalDateTime;

public interface HistoricoInternacaoLeitoProjection {
    String getCodigoLeito();
    String getNomePaciente();
    LocalDateTime getDataInternacao();
    LocalDateTime getDataAlta();
}