package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.projection.HistoricoInternacaoLeitoProjection;
import com.hospitalapp.hospitalapp.projection.InfoPacienteProjection;
import com.hospitalapp.hospitalapp.projection.PacienteQuartoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    Optional<Leito> findByLeitoIdAndQuartoAlaHospitalHospitalId(@Param("leitoId") Long leitoId, @Param("hospitalId") Long hospitalId);

    Optional<Leito> findFirstByQuartoAlaEspecialidadeIgnoreCaseAndQuartoAlaHospitalHospitalIdAndStatus(@Param("especialidade") String especialidade, @Param("hospitalId") Long hospitalId, @Param("status") StatusEnum status);

    Optional<Leito> findByPacientePacienteIdAndQuartoAlaHospitalHospitalId(@Param("pacienteId") Long pacienteId, @Param("hospitalId") Long hospitalId);

    Iterable<Leito> findByStatusAndQuartoAlaEspecialidadeAndQuartoAlaHospitalHospitalId(@Param("status") StatusEnum status, @Param("especialidade") String especialidade, @Param("hospitalId") Long hospitalId);

    @Query("""
                SELECT\s
                    p.nome AS nomePaciente,
                    q.codigoQuarto AS codigoQuarto,
                    a.especialidade AS especialidade,
                    h.nome AS nomeHospital,
                    p.dataInternacao AS dataInternacao
                FROM Leito l
                JOIN l.paciente p
                JOIN l.quarto q
                JOIN q.ala a
                JOIN a.hospital h
                WHERE p.pacienteId = :pacienteId
                AND h.hospitalId = :hospitalId
                AND p.dataAlta IS NULL
            """)
    Optional<InfoPacienteProjection> findInfoPacienteByPacienteId(@Param("pacienteId") Long pacienteId, @Param("hospitalId") Long hospitalId);

    @Query("""
                SELECT\s
                    p.nome AS nomePaciente,
                    q.codigoQuarto AS codigoQuarto
                FROM Leito l
                JOIN l.paciente p
                JOIN l.quarto q
                JOIN q.ala a
                JOIN a.hospital h
                WHERE p.pacienteId = :pacienteId
                AND h.hospitalId = :hospitalId
                AND p.dataAlta IS NULL
            """)
    Optional<PacienteQuartoProjection> findQuartoPaciente(@Param("pacienteId") Long pacienteId, @Param("hospitalId") Long hospitalId);

    Iterable<Leito> findAllByQuartoAlaHospitalHospitalId(@Param("hospitalId") Long hospitalId);

    @Query(value = """
                SELECT 
                    l.codigo AS codigoLeito,
                    p.nome AS nomePaciente,
                    log.data_internacao AS dataInternacao,
                    log.data_alta AS dataAlta
                FROM log_internacao log
                JOIN leito l ON log.leito_id = l.leito_id
                JOIN paciente p ON log.paciente_id = p.paciente_id
                JOIN quarto q ON l.quarto_id = q.quarto_id
                JOIN ala a ON q.ala_id = a.ala_id
                JOIN hospital h ON a.hospital_id = h.hospital_id
                WHERE l.codigo = :codigoLeito
                AND h.hospital_id = :hospitalId
                ORDER BY log.data_internacao DESC
            """, nativeQuery = true)
    List<HistoricoInternacaoLeitoProjection> findHistoricoByCodigoLeito(@Param("codigoLeito") String codigoLeito, @Param("hospitalId") Long hospitalId);
}
