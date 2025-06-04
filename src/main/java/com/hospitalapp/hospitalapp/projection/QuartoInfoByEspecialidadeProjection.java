package com.hospitalapp.hospitalapp.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "especialidade", "quartosLivres", "quartosOcupados", "totalQuartos" })
public interface QuartoInfoByEspecialidadeProjection {
    String getEspecialidade();
    Long getTotalQuartos();
    Long getQuartosLivres();
    Long getQuartosOcupados();
}
