package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    Optional<Leito> findByLeitoIdAndQuartoAlaHospitalHospitalId(Long leitoId, Long hospitalId);

    Optional<Leito> findFirstByQuartoAlaEspecialidadeIgnoreCaseAndQuartoAlaHospitalHospitalIdAndStatus(String especialidade, Long hospitalId, StatusEnum status);

    Optional<Leito> findByPacientePacienteIdAndQuartoAlaHospitalHospitalId(Long pacienteId, Long hospitalId);

    Iterable<Leito> findByStatusAndQuartoAlaEspecialidadeAndQuartoAlaHospitalHospitalId(StatusEnum status, String especialidade, Long hospitalId);
}
