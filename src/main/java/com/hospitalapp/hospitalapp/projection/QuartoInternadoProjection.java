package com.hospitalapp.hospitalapp.projection;

import java.time.LocalDateTime;

public interface QuartoInternadoProjection {
    String getNomePaciente();
    String getCodigoQuarto();
    String getEspecialidade();
    String getNomeHospital();
    LocalDateTime getDataInternacao();
}