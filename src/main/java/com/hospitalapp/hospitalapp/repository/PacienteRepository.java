package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.projection.PacientesInternadosAlfabeticoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByPacienteId(long pacienteId);

    @Query(value = """
    SELECT 
        p.nome AS nomePaciente,
        a.especialidade AS especialidade,
        p.internacao AS dataInternacao,
        DATE_PART('day', NOW() - p.internacao) AS diasInternado
    FROM paciente p
    JOIN leito l ON l.paciente_id = p.paciente_id
    JOIN quarto q ON l.quarto_id = q.quarto_id
    JOIN ala a ON q.ala_id = a.ala_id
    WHERE p.alta IS NULL
        AND a.hospital_id = :hospitalId
    ORDER BY a.especialidade, p.nome
    """, nativeQuery = true)
    List<PacientesInternadosAlfabeticoProjection> findPacientesInternadosAlfabeticamenteByHospitalId(@Param("hospitalId") Long hospitalId);
}