package com.hospitalapp.hospitalapp.projection;

public interface QuartoInfoByEspecialidadeProjection {
    String getEspecialidade();
    Long getTotalQuartos();
    Long getQuartosLivres();
    Long getQuartosEmUso();
}
