package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.projection.QuartoInfoByEspecialidadeProjection;
import com.hospitalapp.hospitalapp.projection.QuartoWithLeitoLiberadoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    Optional<Quarto> findByQuartoIdAndAlaHospitalHospitalId(@Param("quartoId") Long quartoId, @Param("hospitalId")  Long hospitalId);

    @Query(value = """
                SELECT 
                    a.especialidade AS especialidade,
                    COUNT(DISTINCT q.quarto_id) AS totalQuartos,
                    COUNT(DISTINCT CASE 
                        WHEN q.status = 'LIBERADO' THEN q.quarto_id\s
                        END) AS quartosLivres,
                    COUNT(DISTINCT CASE 
                        WHEN q.status = 'OCUPADO' THEN q.quarto_id\s
                        END) AS quartosOcupados
                FROM quarto q
                JOIN ala a ON q.ala_id = a.ala_id
                    WHERE a.hospital_id = :hospitalId
                GROUP BY a.especialidade
            """, nativeQuery = true)
    List<QuartoInfoByEspecialidadeProjection> findInfoQuartosByEspecialidade(@Param("hospitalId") Long hospitalId);

    @Query(value = """
            SELECT 
                a.especialidade AS especialidade,
                q.codigo AS codigoQuarto
            FROM quarto q
            JOIN ala a ON q.ala_id = a.ala_id
            WHERE EXISTS (
                SELECT 1 FROM leito l 
                WHERE l.quarto_id = q.quarto_id AND l.status = 'LIBERADO'
            ) AND a.hospital_id = :hospitalId
            """, nativeQuery = true)
    List<QuartoWithLeitoLiberadoProjection> findQuartosWithLeitoLiberado(@Param("hospitalId") Long hospitalId);


}