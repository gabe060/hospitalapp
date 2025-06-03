package com.hospitalapp.hospitalapp.projection;

import java.time.LocalDateTime;

public interface PacientesInternadosAlfabeticoProjection {
    String getNomePaciente();
    String getEspecialidade();
    LocalDateTime getDataInternacao();
    Integer getDiasInternado();
}
